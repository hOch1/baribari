package community.baribari.service.notice;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.notice.NoticeDto;
import community.baribari.entity.notice.Notice;
import community.baribari.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public void save(NoticeDto noticeDto, PrincipalDetail principalDetail) {
        Notice notice = Notice.toEntity(principalDetail.getMember(), noticeDto);
        noticeRepository.save(notice);

        log.info("{}님이 공지사항을 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), notice.getId());
    }

    public List<NoticeDto> list() {
        List<Notice> notices = noticeRepository.findAll();
        return notices.stream()
                .map(NoticeDto::toDto)
                .toList();
    }

    public Page<NoticeDto> noticePage(Pageable pageable){
        Page<Notice> notices = noticeRepository.findAllByOrderByCreatedAtDesc(pageable);
        return notices.map(NoticeDto::toDto);
    }

    public List<NoticeDto> mainList() {
        List<Notice> notices = noticeRepository.findTop3ByOrderByCreatedAtDesc();
        return notices.stream()
                .map(NoticeDto::toDto)
                .toList();
    }
}
