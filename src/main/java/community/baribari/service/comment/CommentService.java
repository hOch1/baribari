package community.baribari.service.comment;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.comment.CommentDto;
import community.baribari.entity.board.Board;
import community.baribari.entity.comment.Comment;
import community.baribari.exception.BoardNotFoundException;
import community.baribari.exception.CommentNotFoundException;
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
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        Comment comment = Comment.toEntity(commentDto, principalDetail, board);
        Comment save = commentRepository.save(comment);

        log.info("{}님이 {}에 댓글을 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), boardId, save.getId());
    }

    public List<CommentDto> list(Long boardId){
        List<Comment> comments = commentRepository.findByBoardIdAndParentIsNull(boardId);

        return comments.stream().map(CommentDto::toDto).toList();
    }

    @Transactional
    public Comment delete(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        comment.delete();
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment addReply(CommentDto commentDto, Long commentId, PrincipalDetail principalDetail) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        Comment reply = Comment.toEntity(commentDto, principalDetail, comment.getBoard(), comment);
        return commentRepository.save(reply);
    }
}
