package community.baribari.service.board.extend;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.FreeBoardDto;
import community.baribari.entity.board.FreeBoard;
import community.baribari.repository.board.BoardRepository;
import community.baribari.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class FreeBoardService extends BoardService<FreeBoard, FreeBoardDto> {

    public FreeBoardService(BoardRepository<FreeBoard> boardRepository) {
        super(boardRepository);
    }

    @Override
    protected FreeBoard toEntity(FreeBoardDto dto, PrincipalDetail principalDetail) {
        return FreeBoard.toEntity(dto, principalDetail);
    }

    @Override
    protected void updateBoard(FreeBoard board, FreeBoardDto dto) {
        board.update(dto);
    }

    @Override
    protected FreeBoardDto toDto(FreeBoard entity) {
        return FreeBoardDto.toDto(entity);
    }

    @Override
    protected String getBoardTypeName() {
        return "자유 게시판";
    }
}
