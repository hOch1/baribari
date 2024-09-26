package community.baribari.service.star;

import community.baribari.config.PrincipalDetail;
import community.baribari.entity.board.Answer;
import community.baribari.entity.star.AnswerStar;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.board.AnswerRepository;
import community.baribari.repository.star.AnswerStarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AnswerStarService {

    private final AnswerStarRepository answerStarRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    public void starCountUp(Long id, PrincipalDetail principalDetail) {
        Answer answer = answerRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.ANSWER_NOT_FOUND));

        if (answerStarRepository.existsByMemberIdAndAnswerId(principalDetail.getMember().getId(), id))
            throw new CustomException(ErrorCode.ALREADY_STARRED_ANSWER);

        AnswerStar answerStar = AnswerStar.toEntity(principalDetail.getMember(), answer);
        AnswerStar save = answerStarRepository.save(answerStar);

        log.info("{}님이 답변 {}을 추천했습니다. ID : {}", principalDetail.getMember().getNickname(), answer.getId(), save.getId());
    }
}
