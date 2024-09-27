package community.baribari.repository.board.querydsl;

import community.baribari.dto.search.SearchRequest;
import community.baribari.entity.board.Board;
import community.baribari.entity.board.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoardRepositoryCustom<T extends Board> {

    // 검색
    Page<T> boardSearch(Category category, SearchRequest searchRequest, Pageable pageable);

}
