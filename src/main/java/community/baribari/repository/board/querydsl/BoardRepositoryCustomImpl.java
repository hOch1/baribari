package community.baribari.repository.board.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import community.baribari.dto.search.SearchRequest;
import community.baribari.dto.search.SearchType;
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
    public Page<T> boardSearch(Category category, SearchRequest searchRequest, Pageable pageable) {
        String keyword = searchRequest.getKeyword();
        BooleanBuilder builder = new BooleanBuilder();
        QBoard board = QBoard.board;

        if (searchRequest.getSearchType().equals(SearchType.TITLE))
            builder.and(board.title.containsIgnoreCase(keyword));
        else if (searchRequest.getSearchType().equals(SearchType.CONTENT))
            builder.and(board.content.containsIgnoreCase(keyword));
        else if (searchRequest.getSearchType().equals(SearchType.NICKNAME))
            builder.and(board.member.nickname.containsIgnoreCase(keyword));
        else
            builder.and(board.title.containsIgnoreCase(keyword)
                    .or(board.content.containsIgnoreCase(keyword)));

        List<Board> fetch = queryFactory.selectFrom(board)
                .where(board.deleted.isFalse())
                .where(board.category.eq(category))
                .where(builder)
                .orderBy(board.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        return (Page<T>) new PageImpl<>(fetch, pageable, fetch.size());
    }
}
