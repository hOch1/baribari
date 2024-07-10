package community.baribari.repository.star;


import community.baribari.entity.star.CommentStar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentStarRepository extends JpaRepository<CommentStar, Long> {
    boolean existsByMemberIdAndCommentId(Long memberId, Long commentId);
}
