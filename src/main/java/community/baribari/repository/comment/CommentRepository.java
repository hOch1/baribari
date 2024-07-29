package community.baribari.repository.comment;

import community.baribari.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoardIdAndParentIsNull(Long boardId);

}
