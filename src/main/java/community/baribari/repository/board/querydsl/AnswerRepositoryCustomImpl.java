package community.baribari.repository.board.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import community.baribari.dto.search.SearchRequest;
import community.baribari.dto.search.SearchType;
import community.baribari.entity.board.Answer;
import community.baribari.entity.board.QAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnswerRepositoryCustomImpl implements AnswerRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Answer> searchAnswer(SearchRequest searchRequest, Pageable pageable) {
        QAnswer answer = QAnswer.answer;
        String keyword = searchRequest.getKeyword();
        BooleanBuilder builder = new BooleanBuilder();

        if (searchRequest.getSearchType().equals(SearchType.CONTENT))
            builder.and(answer.content.containsIgnoreCase(keyword));
        else if (searchRequest.getSearchType().equals(SearchType.NICKNAME))
            builder.and(answer.member.nickname.containsIgnoreCase(keyword));
        else
            return Page.empty();


        List<Answer> answers = jpaQueryFactory.selectFrom(answer)
                .where(answer.deleted.isFalse())
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(answers, pageable, answers.size());
    }
}
