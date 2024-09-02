package community.baribari.repository.board.querydsl;

import community.baribari.entity.board.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnswerRepositoryCustom {

    // 검색
    Page<Answer> searchAnswer(String keyword, Pageable pageable);
}
