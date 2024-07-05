package community.baribari.repository.star;

import community.baribari.entity.star.FreeBoardStar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardStarRepository extends JpaRepository<FreeBoardStar, Long> {
    boolean existsByMemberId(Long memberId);
}
