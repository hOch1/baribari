package community.baribari.repository.star;

import community.baribari.entity.star.AnswerStar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerStarRepository extends JpaRepository<AnswerStar, Long> {
    boolean existsByMemberIdAndAnswerId(Long memberId, Long answerId);
}
