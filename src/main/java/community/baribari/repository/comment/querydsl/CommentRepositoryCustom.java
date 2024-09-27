package community.baribari.repository.comment.querydsl;

import community.baribari.entity.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CommentRepositoryCustom {

    // 검색
    Page<Comment> commentSearch(String keyword, Pageable pageable);
}
