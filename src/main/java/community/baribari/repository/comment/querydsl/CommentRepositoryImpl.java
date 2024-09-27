package community.baribari.repository.comment.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import community.baribari.dto.search.SearchRequest;
import community.baribari.dto.search.SearchType;
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
    public Page<Comment> commentSearch(SearchRequest searchRequest, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        QComment comment = QComment.comment;
        String keyword = searchRequest.getKeyword();

        if (searchRequest.getSearchType().equals(SearchType.CONTENT))
            builder.and(comment.content.containsIgnoreCase(keyword));
        else if (searchRequest.getSearchType().equals(SearchType.NICKNAME))
            builder.and(comment.member.nickname.containsIgnoreCase(keyword));
        else
            return Page.empty();


        List<Comment> fetch = jpaQueryFactory.selectFrom(QComment.comment)
                .where(comment.deleted.isFalse())
                .where(builder)
                .orderBy(comment.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(fetch, pageable, fetch.size());
    }
}
