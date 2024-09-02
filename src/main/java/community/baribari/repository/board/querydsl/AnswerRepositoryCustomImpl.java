package community.baribari.repository.board.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
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
    public Page<Answer> searchAnswer(String keyword, Pageable pageable) {
        QAnswer answer = QAnswer.answer;

        List<Answer> answers = jpaQueryFactory.selectFrom(answer)
                .where(answer.content.contains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(answers, pageable, answers.size());
    }
}
