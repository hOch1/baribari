package community.baribari.repository.board;

import community.baribari.entity.board.Board;
import community.baribari.entity.board.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository<T extends Board> extends JpaRepository<T, Long> {

    // 메인 리스트
    List<T> findTop3ByCategoryAndDeletedFalseOrderByCreatedAtDesc(Category category);

    // 카테고리별 리스트
    Page<T> findAllByCategoryAndDeletedFalseOrderByCreatedAtDesc(Category category, Pageable pageable);

    // 작성 글 리스트
    Page<T> findByDeletedFalseAndCategoryAndMemberIdOrderByCreatedAtDesc(Category category, Long id, Pageable pageable);

    // 검색
    Page<T> findByDeletedFalseAndCategoryAndTitleOrContentContainingOrderByCreatedAtDesc(Category category, String titleKeyword, String contentKeyword, Pageable pageable);
}
