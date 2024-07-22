package community.baribari.repository.board;

import community.baribari.entity.board.Board;
import community.baribari.entity.board.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository<T extends Board> extends JpaRepository<T, Long> {

    List<T> findTop3ByDeletedFalseOrderByCreatedAtDesc();


    Page<T> findAllByCategoryAndDeletedFalseOrderByCreatedAtDesc(Category category, Pageable pageable);
}
