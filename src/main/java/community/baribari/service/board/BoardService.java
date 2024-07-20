package community.baribari.service.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.FreeBoardDto;
import community.baribari.entity.board.Board;
import community.baribari.entity.board.FreeBoard;
import community.baribari.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void freeBoardSave(FreeBoardDto freeBoardDto, PrincipalDetail principalDetail) {
        FreeBoard freeBoard = FreeBoard.toEntity(freeBoardDto, principalDetail);
        boardRepository.save(freeBoard);
    }

}
