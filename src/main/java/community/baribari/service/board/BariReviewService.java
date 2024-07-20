package community.baribari.service.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.BariReviewDto;
import community.baribari.entity.board.BariReview;
import community.baribari.repository.board.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class BariReviewService extends BoardService<BariReview, BariReviewDto>{

    public BariReviewService(BoardRepository<BariReview> boardRepository) {
        super(boardRepository);
    }

    @Override
    protected BariReview toEntity(BariReviewDto dto, PrincipalDetail principalDetail) {
        return BariReview.toEntity(dto, principalDetail);
    }
    @Override
    protected BariReviewDto toDto(BariReview entity) {
        return BariReviewDto.toDto(entity);
    }

    @Override
    protected void updateBoard(BariReview board, BariReviewDto dto) {
        board.update(dto);
    }

    @Override
    protected String getBoardTypeName() {
        return "바리모집";
    }
}
