package community.baribari.repository.comment.querydsl;

import community.baribari.dto.search.SearchRequest;
import community.baribari.entity.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CommentRepositoryCustom {

    // 검색
    Page<Comment> commentSearch(SearchRequest searchRequest, Pageable pageable);
}
