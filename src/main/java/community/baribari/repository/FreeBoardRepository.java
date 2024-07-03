package community.baribari.repository;

import community.baribari.entity.board.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

    List<FreeBoard> findTop3ByOrderByCreatedAtDesc();


    Page<FreeBoard> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
