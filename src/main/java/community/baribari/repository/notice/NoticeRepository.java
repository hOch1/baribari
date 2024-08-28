package community.baribari.repository.notice;

import community.baribari.entity.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findTop3ByOrderByCreatedAtDesc();
    Page<Notice> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
