package community.baribari.service.report;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.report.ReportDto;
import community.baribari.entity.board.Category;
import community.baribari.entity.report.Report;
import community.baribari.repository.report.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public abstract class ReportService<T extends Report, D extends ReportDto> {

    private final ReportRepository<T> reportRepository;

    @Transactional
    public void save(D reportDto, PrincipalDetail principalDetail, Long id) throws Throwable {
        T report = toEntity(reportDto, principalDetail, id);
        T savedReport = reportRepository.save(report);

        log.info("{}님이 신고하신 {}을 신고하였습니다.", principalDetail.getMember().getNickname(), savedReport.getId());
    }

    public D detail(Long id) {
        T report = reportRepository.findById(id).orElseThrow();
        return toDto(report);
    }


    protected abstract T toEntity(D dto, PrincipalDetail principalDetail, Long id) throws Throwable;
    protected abstract D toDto(T entity);

}
