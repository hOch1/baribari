package community.baribari.repository.star;


import community.baribari.entity.star.BoardStar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardStarRepository extends JpaRepository<BoardStar, Long> {
    boolean existsByMemberIdAndBoardId(Long memberId, Long boardId);
}
