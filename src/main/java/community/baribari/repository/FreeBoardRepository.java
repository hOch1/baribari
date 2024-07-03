package community.baribari.repository;

import community.baribari.entity.board.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

    Page<FreeBoard> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
