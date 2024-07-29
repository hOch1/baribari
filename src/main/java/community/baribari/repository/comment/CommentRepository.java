package community.baribari.repository.comment;

import community.baribari.entity.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 상위 댓글 조회
    List<Comment> findByBoardIdAndParentIsNull(Long boardId);

    // 검색
    Page<Comment> findByDeletedFalseAndContentContainingOrderByCreatedAtDesc(String keyword, Pageable pageable);

    // 작성한 댓글
    Page<Comment> findByDeletedFalseAndMemberIdOrderByCreatedAtDesc(Long memberId, Pageable pageable);
}
