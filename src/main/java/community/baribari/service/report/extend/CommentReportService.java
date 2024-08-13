package community.baribari.service.report.extend;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.report.CommentReportDto;
import community.baribari.entity.comment.Comment;
import community.baribari.entity.report.CommentReport;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.comment.CommentRepository;
import community.baribari.repository.report.ReportRepository;
import community.baribari.service.report.ReportService;
import org.springframework.stereotype.Service;

@Service
public class CommentReportService extends ReportService<CommentReport, CommentReportDto> {

    private final CommentRepository commentRepository;

    public CommentReportService(ReportRepository<CommentReport> reportRepository, CommentRepository commentRepository, CommentRepository commentRepository1) {
        super(reportRepository);
        this.commentRepository = commentRepository1;
    }

    @Override
    protected CommentReport toEntity(CommentReportDto dto, PrincipalDetail principalDetail, Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        return CommentReport.toEntity(dto, comment, principalDetail.getMember());
    }

    @Override
    protected CommentReportDto toDto(CommentReport entity) {
        return CommentReportDto.toDto(entity);
    }
}
