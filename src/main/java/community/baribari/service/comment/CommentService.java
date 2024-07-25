package community.baribari.service.comment;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.comment.CommentDto;
import community.baribari.entity.board.Board;
import community.baribari.entity.comment.Comment;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.board.BoardRepository;
import community.baribari.repository.board.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CommentService {

    private final BoardRepository<Board> boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void addComment(CommentDto commentDto, PrincipalDetail principalDetail, Long boardId){
        try {
            Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));

            Comment comment = Comment.toEntity(commentDto, principalDetail, board);
            Comment save = commentRepository.save(comment);

            log.info("{}님이 {}에 댓글을 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), boardId, save.getId());
        }catch (Exception e){
            log.error("댓글 등록 중 오류 발생 : {}", e.getMessage());
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public List<CommentDto> list(Long boardId){
        List<Comment> comments = commentRepository.findByBoardIdAndParentIsNull(boardId);

        return comments.stream().map(CommentDto::toDto).toList();
    }

    @Transactional
    public Comment delete(Long id){
        try {
            Comment comment = commentRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
            comment.delete();
            return commentRepository.save(comment);
        }catch (Exception e){
            log.error("댓글 삭제 중 오류 발생 : {}", e.getMessage());
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void addReply(CommentDto commentDto, Long commentId, PrincipalDetail principalDetail) {
        try {
            Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
            Comment reply = Comment.toEntity(commentDto, principalDetail, comment.getBoard(), comment);
            commentRepository.save(reply);
        }catch (Exception e){
            log.error("대댓글 등록 중 오류 발생 : {}", e.getMessage());
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
