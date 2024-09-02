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
    public List<Comment> findByBoardIdAndParentIsNull(Long boardId) {
        return jpaQueryFactory.selectFrom(QComment.comment)
                .where(QComment.comment.board.id.eq(boardId).and(QComment.comment.parent.isNull()))
                .fetch();
    }

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

    @Override
    public Page<Comment> findByDeletedFalseAndMemberIdOrderByCreatedAtDesc(Long memberId, Pageable pageable) {
        List<Comment> fetch = jpaQueryFactory.selectFrom(QComment.comment)
                .where(QComment.comment.deleted.isFalse().and(QComment.comment.member.id.eq(memberId)))
                .orderBy(QComment.comment.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(fetch, pageable, fetch.size());
    }
}
