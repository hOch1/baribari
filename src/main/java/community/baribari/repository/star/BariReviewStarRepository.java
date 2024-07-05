package community.baribari.repository.star;

import community.baribari.entity.star.BariReviewStar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BariReviewStarRepository extends JpaRepository<BariReviewStar, Long> {
    boolean existsByMemberId(Long memberId);
}
