package community.baribari.service.note;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.note.NoteBoxDto;
import community.baribari.dto.note.NoteDto;
import community.baribari.dto.sse.Notification;
import community.baribari.entity.member.AccountSetting;
import community.baribari.entity.member.Member;
import community.baribari.entity.note.Note;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.member.MemberRepository;
import community.baribari.repository.note.NoteRepository;
import community.baribari.service.sse.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NoteService noteService;

    private Member member;
    private Pageable pageable;
    private PrincipalDetail principalDetail;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                .id(1L)
                .accountSetting(AccountSetting.builder().build())
                .build();

        principalDetail = new PrincipalDetail(member);
        pageable = PageRequest.of(0, 10);
    }

    @Test
    @DisplayName("쪽지_조회")
    void getNotes() {
        Page<Note> emptyNotesPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

        when(noteRepository.findByReceiveIdOrSendIdOrderByCreatedAtDesc(anyLong(), eq(pageable))).thenReturn(emptyNotesPage);
        when(noteRepository.findByReceiveIdAndIsReadFalseOrderByCreatedAtDesc(anyLong(), eq(pageable))).thenReturn(emptyNotesPage);
        when(noteRepository.findByReceiveIdOrderByCreatedAtDesc(anyLong(), eq(pageable))).thenReturn(emptyNotesPage);
        when(noteRepository.findBySendIdOrderByCreatedAtDesc(anyLong(), eq(pageable))).thenReturn(emptyNotesPage);

        NoteBoxDto result = noteService.getNotes(principalDetail, pageable);

        assertNotNull(result);
        assertEquals(0, result.getAllNotes().getTotalElements());
        assertEquals(0, result.getReceiveNotes().getTotalElements());
        assertEquals(0, result.getSendNotes().getTotalElements());
        assertEquals(0, result.getUnreadNotes().getTotalElements());

        verify(noteRepository).findByReceiveIdOrSendIdOrderByCreatedAtDesc(anyLong(), eq(pageable));
        verify(noteRepository).findByReceiveIdAndIsReadFalseOrderByCreatedAtDesc(anyLong(), eq(pageable));
        verify(noteRepository).findByReceiveIdOrderByCreatedAtDesc(anyLong(), eq(pageable));
        verify(noteRepository).findBySendIdOrderByCreatedAtDesc(anyLong(), eq(pageable));
    }

    @Test
    @DisplayName("차단_여부_확인_isBlock")
    void isBlock() {
        AccountSetting accountSetting = AccountSetting.builder()
                .noteBlock(true)
                .build();

        Member receive = Member.builder()
                .id(1L)
                .accountSetting(accountSetting)
                .build();

        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(receive));

        CustomException customException = assertThrows(CustomException.class, () -> noteService.isBlock(1L));

        assertEquals(ErrorCode.NOTE_BLOCK, customException.getErrorCode());
        verify(memberRepository).findById(member.getId());
    }


    @Test
    @DisplayName("쪽지_보내기")
    void sendNote() {
        NoteDto noteDto = NoteDto.builder().build();
        Member receive = Member.builder()
                .id(2L)
                .accountSetting(AccountSetting.builder().build())
                .build();

        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member));

        noteService.sendNote(principalDetail, receive.getId(), noteDto);

        verify(memberRepository).findById(receive.getId());
        verify(noteRepository).save(any(Note.class));
        verify(notificationService).sendNotification(eq(receive.getId()), eq(Notification.NEW_NOTE.getMessage()), eq("/note"));
    }

    @Test
    @DisplayName("쪽지_읽기")
    void readNote() {
        Note note = Note.builder()
                .receive(member)
                .send(member)
                .build();

        when(noteRepository.findById(anyLong())).thenReturn(Optional.of(note));

        NoteDto noteDto = noteService.readNote(1L);

        assertTrue(noteDto.isRead());
        verify(noteRepository).findById(1L);
        verify(noteRepository).save(note);
    }

    @Test
    @DisplayName("쪽지_검색")
    void searchNotes() {
        Page<Note> emptyNotesPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

        when(noteRepository.noteSearch(anyLong(), eq("test"), eq(pageable))).thenReturn(emptyNotesPage);

        Page<NoteDto> result = noteService.searchNotes(principalDetail, "test", pageable);

        assertNotNull(result);
        assertEquals(0, result.getTotalElements());

        verify(noteRepository).noteSearch(anyLong(), eq("test"), eq(pageable));
    }
}