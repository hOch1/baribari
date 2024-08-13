package community.baribari.repository.report;

import community.baribari.entity.report.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository<T extends Report> extends JpaRepository<T, Long> {

    Page<T> findByMemberId(Long memberId, Pageable pageable);
}
