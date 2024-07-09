package community.baribari.repository.star;


import community.baribari.entity.star.Star;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRepository extends JpaRepository<Star, Long> {
    boolean existsByMemberIdAndBoardId(Long memberId, Long boardId);
}
