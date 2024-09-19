package community.baribari.service.notice;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.notice.NoticeDto;
import community.baribari.entity.member.Member;
import community.baribari.repository.notice.NoticeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NoticeServiceTest {

    @Mock
    private NoticeRepository noticeRepository;

    @InjectMocks
    private NoticeService noticeService;

    @Test
    @DisplayName("공지사항_저장")
    void save() {
        NoticeDto noticeDto = mock(NoticeDto.class);
        PrincipalDetail principalDetail = new PrincipalDetail(mock(Member.class));

        noticeService.save(noticeDto, principalDetail);

        verify(noticeRepository).save(any());
    }

    @Test
    void list() {
    }

    @Test
    void noticePage() {
    }

    @Test
    void mainList() {
    }
}