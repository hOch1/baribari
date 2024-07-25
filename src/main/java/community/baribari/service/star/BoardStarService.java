package community.baribari.service.star;

import community.baribari.config.PrincipalDetail;
import community.baribari.entity.board.Board;
import community.baribari.entity.star.BoardStar;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.board.BoardRepository;
import community.baribari.repository.star.BoardStarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BoardStarService {

    private final BoardRepository<Board> boardRepository;
    private final BoardStarRepository boardStarRepository;

    @Transactional
    public void starCountUp(Long id, PrincipalDetail principalDetail) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));

        if (boardStarRepository.existsByMemberIdAndBoardId(principalDetail.getMember().getId(), id))
            throw new CustomException(ErrorCode.ALREADY_STARRED_BOARD);

        BoardStar boardStar = BoardStar.builder()
                .member(principalDetail.getMember())
                .board(board)
                .build();

        BoardStar save = boardStarRepository.save(boardStar);
        log.info("{}님이 게시물 {}을 추천했습니다. ID : {}", principalDetail.getMember().getNickname(), board.getId(), save.getId());
    }
}
