package community.baribari.service.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.BoardDto;
import community.baribari.dto.search.SearchRequest;
import community.baribari.entity.board.Board;
import community.baribari.entity.board.Category;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
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

    public Page<D> list(Pageable pageable) {
        Page<T> boards = boardRepository.findByCategoryAndDeletedFalseOrderByCreatedAtDesc(getCategory() ,pageable);
        return boards.map(this::toDto);
    }

    public List<D> mainList() {
        List<T> boards = boardRepository.findTop3ByCategoryAndDeletedFalseOrderByCreatedAtDesc(getCategory());
        return boards.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Page<D> myList(PrincipalDetail principalDetail, Pageable pageable){
        Page<T> boards = boardRepository.findByMember(getCategory(), principalDetail.getMember(), pageable);
        return boards.map(this::toDto);
    }

    public Page<D> search(SearchRequest searchRequest, Pageable pageable){
        Page<T> boards = boardRepository.boardSearch(getCategory(), searchRequest, pageable);
        return boards.map(this::toDto);
    }

    public D detail(Long id) {
        T board = boardRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.BOARD_NOT_FOUND));

        if (board.getDeleted())
            throw new CustomException(ErrorCode.DELETED_BOARD);

        return toDto(board);
    }

    @Transactional
    public void update(D boardDto) {
        T board = boardRepository.findById(boardDto.getId()).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));

        updateBoard(board, boardDto);
        boardRepository.save(board);

        log.info("{}님이 게시물 {}을 수정하였습니다.", board.getMember().getId(), board.getId());
    }

    @Transactional
    public void viewCountUp(Long id) {
        T board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));

        if (board.getDeleted())
            throw new CustomException(ErrorCode.DELETED_BOARD);

        board.updateViewCount();
        boardRepository.save(board);
    }

    @Transactional
    public void delete(Long id){
        T board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        board.delete();
        boardRepository.save(board);

        log.info("{}님이 게시물 {}을 삭제했습니다.", board.getMember().getId(), board.getId());
    }

    protected abstract T toEntity(D dto, PrincipalDetail principalDetail);
    protected abstract void updateBoard(T board, D dto);
    protected abstract D toDto(T entity);
    protected abstract String getBoardTypeName();
    protected abstract Category getCategory();
}
