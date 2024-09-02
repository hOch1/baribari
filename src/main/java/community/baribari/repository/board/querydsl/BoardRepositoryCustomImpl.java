package community.baribari.repository.board.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import community.baribari.entity.board.Board;
import community.baribari.entity.board.Category;
import community.baribari.entity.board.QBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl<T extends Board> implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<T> mainList(Category category) {
        QBoard board = QBoard.board;

        List<Board> fetch = queryFactory.selectFrom(board)
                .where(board.deleted.isFalse()
                        .and(board.category.eq(category)))
                .orderBy(board.createdAt.desc())
                .limit(3)
                .fetch();

        return fetch.stream()
                .map(entity -> (T) entity)
                .toList();
    }

    @Override
    public Page<T> listByCategory(Category category, Pageable pageable) {
        QBoard board = QBoard.board;

        List<Board> fetch = queryFactory.selectFrom(board)
                .where(board.deleted.isFalse()
                        .and(board.category.eq(category)))
                .orderBy(board.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(fetch, pageable, fetch.size())
                .map(entity -> (T) entity);
    }

    @Override
    public Page<T> writeBoardList(Category category, Long id, Pageable pageable) {
        QBoard board = QBoard.board;

        List<Board> fetch = queryFactory.selectFrom(board)
                .where(board.deleted.isFalse()
                        .and(board.category.eq(category))
                        .and(board.member.id.eq(id)))
                .orderBy(board.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(fetch, pageable, fetch.size())
                .map(entity -> (T) entity);
    }

    @Override
    public Page<T> boardSearch(Category category, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(board.deleted.isFalse());
        builder.and(board.category.eq(category));
        builder.and(board.title.contains(keyword).or(board.content.contains(keyword)));

        return null;
    }
}
