package community.baribari.repository.star;

import community.baribari.entity.star.QnABoardStar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnABoardStarRepository extends JpaRepository<QnABoardStar, Long> {

    boolean existsByMemberId(Long memberId);
}
