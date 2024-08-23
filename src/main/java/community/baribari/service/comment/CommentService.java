package community.baribari.service.comment;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.comment.CommentDto;
import community.baribari.entity.board.Board;
import community.baribari.entity.comment.Comment;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.board.BoardRepository;
import community.baribari.repository.comment.CommentRepository;
import community.baribari.service.sse.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final NotificationService notificationService;

    @Transactional
    public void addComment(CommentDto commentDto, PrincipalDetail principalDetail, Long boardId){
        try {
            Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));

            Comment comment = Comment.toEntity(commentDto, principalDetail, board);
            Comment save = commentRepository.save(comment);

            log.info("{}님이 {}에 댓글을 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), boardId, save.getId());

            Long boardOwnerId = board.getMember().getId();
            String message = principalDetail.getMember().getNickname() + "님이 게시물 '" + board.getTitle() + "'에 댓글을 남겼습니다.";

            if (!principalDetail.getMember().getId().equals(boardOwnerId))
                notificationService.sendNotification(boardOwnerId, message);
        }catch (Exception e){
            log.error("댓글 등록 중 오류 발생 : {}", e.getMessage());
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public CommentDto detail(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        return CommentDto.toDto(comment);
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

            if (!principalDetail.getMember().getId().equals(reply.getMember().getId()))
                notificationService.sendNotification(comment.getMember().getId(), principalDetail.getMember().getNickname() + "님이 댓글에 답글을 남겼습니다.");
        }catch (Exception e){
            log.error("대댓글 등록 중 오류 발생 : {}", e.getMessage());
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public Page<CommentDto> search(String keyword, Pageable pageable) {
        return commentRepository.findByDeletedFalseAndContentContainingOrderByCreatedAtDesc(keyword, pageable)
                .map(CommentDto::toDto);
    }

    public Page<CommentDto> myList(Long id, Pageable pageable) {
        return commentRepository.findByDeletedFalseAndMemberIdOrderByCreatedAtDesc(id, pageable)
                .map(CommentDto::toDto);
    }
}
