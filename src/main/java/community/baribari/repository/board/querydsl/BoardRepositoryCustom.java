package community.baribari.repository.board.querydsl;

import community.baribari.entity.board.Board;
import community.baribari.entity.board.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom<T extends Board> {

    // 메인 리스트
    List<T> mainList(Category category);

    // 카테고리별 리스트
    Page<T> listByCategory(Category category, Pageable pageable);

    // 작성 글 리스트
    Page<T> writeBoardList(Category category, Long id, Pageable pageable);

    // 검색
    Page<T> boardSearch(Category category, String keyword, Pageable pageable);

}
