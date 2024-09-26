package community.baribari.repository.comment.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import community.baribari.entity.comment.Comment;
import community.baribari.entity.comment.QComment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Comment> findByDeletedFalseAndContentContainingOrderByCreatedAtDesc(String keyword, Pageable pageable) {
        List<Comment> fetch = jpaQueryFactory.selectFrom(QComment.comment)
                .where(QComment.comment.deleted.isFalse().and(QComment.comment.content.contains(keyword)))
                .orderBy(QComment.comment.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(fetch, pageable, fetch.size());
    }
}
