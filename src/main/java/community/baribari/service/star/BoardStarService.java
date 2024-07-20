package community.baribari.service.star;

import community.baribari.config.PrincipalDetail;
import community.baribari.entity.board.Board;
import community.baribari.entity.star.BoardStar;
import community.baribari.exception.BoardNotFoundException;
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
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);

        if (boardStarRepository.existsByMemberIdAndBoardId(principalDetail.getMember().getId(), id))
            throw new IllegalArgumentException("이미 추천한 게시물입니다.");

        BoardStar boardStar = BoardStar.builder()
                .member(principalDetail.getMember())
                .board(board)
                .build();

        BoardStar save = boardStarRepository.save(boardStar);
        log.info("{}님이 게시물 {}을 추천했습니다. ID : {}", principalDetail.getMember().getNickname(), board.getId(), save.getId());
    }
}
