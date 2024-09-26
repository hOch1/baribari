package community.baribari.repository.board.querydsl;

import community.baribari.entity.board.Board;
import community.baribari.entity.board.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom<T extends Board> {

    // 검색
    Page<T> boardSearch(Category category, String keyword, Pageable pageable);

}
