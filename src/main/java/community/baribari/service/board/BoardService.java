package community.baribari.service.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.BoardDto;
import community.baribari.entity.board.Board;
import community.baribari.entity.board.Category;
import community.baribari.exception.BoardNotFoundException;
import community.baribari.exception.IsDeletedException;
import community.baribari.repository.board.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public abstract class BoardService<T extends Board, D extends BoardDto> {

    private final BoardRepository<T> boardRepository;

    @Transactional
    public void save(D boardDto, PrincipalDetail principalDetail) {
        T board = toEntity(boardDto, principalDetail);
        T savedBoard = boardRepository.save(board);

        log.info("{}님이 {}을 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), getBoardTypeName(), savedBoard.getId());
    }

    public Page<D> list(Category category, Pageable pageable) {
        Page<T> boards = boardRepository.findAllByCategoryAndDeletedFalseOrderByCreatedAtDesc(category, pageable);
        return boards.map(this::toDto);
    }

    public List<D> mainList(Category category) {
        List<T> boards = boardRepository.findTop3ByCategoryAndDeletedFalseOrderByCreatedAtDesc(category);
        return boards.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<D> myList(Category category, Long id){
        List<T> boards = boardRepository.findByCategoryAndMemberId(category, id);
        return boards.stream().map(this::toDto).collect(Collectors.toList());
    }

    public D detail(Long id) {
        T board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        if (board.getDeleted()) {
            throw new IsDeletedException();
        }
        return toDto(board);
    }

    @Transactional
    public void update(D boardDto) {
        T board = boardRepository.findById(boardDto.getId())
                .orElseThrow(BoardNotFoundException::new);

        updateBoard(board, boardDto);
        boardRepository.save(board);

        log.info("{}님이 게시물 {}을 수정하였습니다.", board.getMember().getId(), board.getId());
    }

    @Transactional
    public void viewCountUp(Long id) {
        T board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        board.updateViewCount();
        boardRepository.save(board);
    }

    @Transactional
    public void delete(Long id){
        T board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        board.delete();
        boardRepository.save(board);

        log.info("{}님이 게시물 {}을 삭제했습니다.", board.getMember().getId(), board.getId());
    }

    protected abstract T toEntity(D dto, PrincipalDetail principalDetail);
    protected abstract void updateBoard(T board, D dto);
    protected abstract D toDto(T entity);
    protected abstract String getBoardTypeName();
}
