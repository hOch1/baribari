package community.baribari.repository.star;

import community.baribari.entity.star.BariRecruitStar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BariRecruitStarRepository extends JpaRepository<BariRecruitStar, Long> {
    boolean existsByMemberId(Long memberId);
}
