package community.baribari.service.board.extend;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.QnABoardDto;
import community.baribari.entity.board.QnABoard;
import community.baribari.repository.board.BoardRepository;
import community.baribari.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@Transactional(readOnly = true)
public class QnABoardService extends BoardService<QnABoard, QnABoardDto> {

    public QnABoardService(BoardRepository<QnABoard> boardRepository) {
        super(boardRepository);
    }


    @Override
    protected QnABoard toEntity(QnABoardDto dto, PrincipalDetail principalDetail) {
        return QnABoard.toEntity(dto, principalDetail);
    }

    @Override
    protected void updateBoard(QnABoard board, QnABoardDto dto) {
        board.update(dto);
    }

    @Override
    protected QnABoardDto toDto(QnABoard entity) {
        return QnABoardDto.toDto(entity);
    }

    @Override
    protected String getBoardTypeName() {
        return "질문 게시판";
    }
}
