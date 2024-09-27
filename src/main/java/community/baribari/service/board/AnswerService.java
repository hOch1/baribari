package community.baribari.service.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.AnswerDto;
import community.baribari.dto.search.SearchRequest;
import community.baribari.dto.sse.Notification;
import community.baribari.entity.board.Answer;
import community.baribari.entity.board.QnABoard;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.board.AnswerRepository;
import community.baribari.repository.board.BoardRepository;
import community.baribari.service.sse.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AnswerService  {

    private final AnswerRepository answerRepository;
    private final BoardRepository<QnABoard> boardRepository;
    private final NotificationService notificationService;

    @Transactional
    public void save(Long questionId, PrincipalDetail principalDetail, AnswerDto answerDto) {
        QnABoard qnABoard = boardRepository.findById(questionId).orElseThrow(() ->
                new CustomException(ErrorCode.BOARD_NOT_FOUND));

        Answer answer = Answer.toEntity(answerDto, principalDetail.getMember(), qnABoard);

        Answer save = answerRepository.save(answer);
        log.info("{}님이 {}에 답변을 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), qnABoard.getId(), save.getId());

        if (!qnABoard.getMember().getId().equals(principalDetail.getMember().getId()))
            notificationService.sendNotification(qnABoard.getMember().getId(), Notification.NEW_ANSWER.getMessage(), "/qna-board/detail/" + qnABoard.getId());

    }

    @Transactional
    public void delete(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.ANSWER_NOT_FOUND));

        answerRepository.save(answer.delete());
        log.info("답변이 삭제되었습니다. ID : {}", id);
    }

    @Transactional
    public void accept(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.ANSWER_NOT_FOUND));
        QnABoard qnABoard = answer.getQnaBoard();

        if (qnABoard.getAnswers().stream().anyMatch(Answer::isAccepted))
            throw new CustomException(ErrorCode.ANSWER_ALREADY_ACCEPTED);

        answer.accept();
        answerRepository.save(answer);
        log.info("답변이 채택되었습니다. ID : {}", id);

        notificationService.sendNotification(qnABoard.getMember().getId(), Notification.ANSWER_ACCEPTED.getMessage(), "/qna-board/detail/" + qnABoard.getId());
    }

    @Transactional
    public void update(Long id, AnswerDto answerDto, PrincipalDetail principalDetail) {
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.ANSWER_NOT_FOUND));

        if (principalDetail.getMember().getId().equals(answer.getMember().getId()))
            throw new CustomException(ErrorCode.UNAUTHORIZED);

        answerRepository.save(answer.update(answerDto));
        log.info("답변이 수정되었습니다. ID : {}", id);
    }

    public Page<AnswerDto> search(SearchRequest searchRequest, Pageable pageable) {
        return answerRepository.searchAnswer(searchRequest, pageable)
                .map(AnswerDto::toDto);
    }
}
