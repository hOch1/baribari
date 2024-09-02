package community.baribari.repository.board;

import community.baribari.entity.board.Answer;
import community.baribari.repository.board.querydsl.AnswerRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>, AnswerRepositoryCustom {

    Page<Answer> searchAnswer(String keyword, Pageable pageable);
}
