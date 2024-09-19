package community.baribari.service.report.extend;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.report.BoardReportDto;
import community.baribari.entity.board.Board;
import community.baribari.entity.report.BoardReport;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.board.BoardRepository;
import community.baribari.repository.report.ReportRepository;
import community.baribari.service.report.ReportService;
import org.springframework.stereotype.Service;

@Service
public class BoardReportService extends ReportService<BoardReport, BoardReportDto> {

    private final BoardRepository<Board> boardRepository;

    public BoardReportService(ReportRepository<BoardReport> reportRepository, BoardRepository<Board> boardRepository) {
        super(reportRepository);
        this.boardRepository = boardRepository;
    }


    @Override
    protected BoardReport toEntity(BoardReportDto dto, PrincipalDetail principalDetail, Long id) throws Throwable {
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        return BoardReport.toEntity(dto, board, principalDetail.getMember());
    }

    @Override
    protected BoardReportDto toDto(BoardReport entity) {
        return BoardReportDto.toDto(entity);
    }
}
