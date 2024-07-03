package community.baribari.repository;

import community.baribari.entity.board.QnABoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnABoardRepository extends JpaRepository<QnABoard, Long> {

    Page<QnABoard> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
