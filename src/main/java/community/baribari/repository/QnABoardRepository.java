package community.baribari.repository;

import community.baribari.entity.board.QnABoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnABoardRepository extends JpaRepository<QnABoard, Long> {
}
