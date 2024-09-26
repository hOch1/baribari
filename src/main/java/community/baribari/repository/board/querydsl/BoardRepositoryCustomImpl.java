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
public class BoardRepositoryCustomImpl<T extends Board> implements BoardRepositoryCustom<T> {

    private final JPAQueryFactory queryFactory;

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
