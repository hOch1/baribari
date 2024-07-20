package community.baribari.service.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.AnswerDto;
import community.baribari.entity.board.Answer;
import community.baribari.entity.board.QnABoard;
import community.baribari.exception.BoardNotFoundException;
import community.baribari.repository.board.AnswerRepository;
import community.baribari.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AnswerService  {

    private final AnswerRepository answerRepository;
    private final BoardRepository<QnABoard> boardRepository;

    @Transactional
    public void writeAnswer(Long questionId, PrincipalDetail principalDetail, AnswerDto answerDto) {
        QnABoard qnABoard = boardRepository.findById(questionId).orElseThrow(BoardNotFoundException::new);

        Answer answer = Answer.toEntity(answerDto, principalDetail.getMember(), qnABoard);

        Answer save = answerRepository.save(answer);
        log.info("{}님이 {}에 답변을 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), qnABoard.getId(), save.getId());
    }

}
