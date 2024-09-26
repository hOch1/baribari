package community.baribari.repository.comment.querydsl;

import community.baribari.entity.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentRepositoryCustom {

    // 검색
    Page<Comment> findByDeletedFalseAndContentContainingOrderByCreatedAtDesc(String keyword, Pageable pageable);
}
