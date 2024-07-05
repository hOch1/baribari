package community.baribari.repository.board;

import community.baribari.entity.board.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQnaBoardId(Long qnaBoardId);
}
