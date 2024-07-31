package community.baribari.service.note;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.member.MemberDto;
import community.baribari.dto.note.NoteBoxDto;
import community.baribari.dto.note.NoteDto;
import community.baribari.entity.member.Member;
import community.baribari.entity.note.Note;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.member.MemberRepository;
import community.baribari.repository.note.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final MemberRepository memberRepository;

    public NoteBoxDto getNotes(PrincipalDetail principalDetail, Pageable pageable) {
        Long id = principalDetail.getMember().getId();
        Page<Note> allNotes = noteRepository.findAllByReceiveIdAndSendId(id, id, pageable);
        Page<Note> unreadNotes = noteRepository.findAllByReceiveIdAndSendIdAndIsReadFalse(id, id, pageable);
        Page<Note> receiveNotes = noteRepository.findAllByReceiveId(id, pageable);
        Page<Note> sendNotes = noteRepository.findAllBySendId(id, pageable);

        return NoteBoxDto.builder()
                .allNotes(allNotes.map(NoteDto::toDto))
                .receiveNotes(receiveNotes.map(NoteDto::toDto))
                .sendNotes(sendNotes.map(NoteDto::toDto))
                .unreadNotes(unreadNotes.map(NoteDto::toDto))
                .build();
    }

    public MemberDto isBlock(Long receiveId) {
        Member receive = memberRepository.findById(receiveId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        if (receive.getAccountSetting().isNoteBlock())
            throw new CustomException(ErrorCode.NOTE_BLOCK);

        return MemberDto.toDto(receive);
    }

    @Transactional
    public void sendNote(PrincipalDetail principalDetail, Long receiveId, NoteDto noteDto) {
        Member send = principalDetail.getMember();
        Member receive = memberRepository.findById(receiveId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        Note note = Note.toEntity(noteDto, send, receive);
        noteRepository.save(note);
    }

    public NoteDto readNote(Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOTE_NOT_FOUND));
        note.read();
        return NoteDto.toDto(note);
    }
}
