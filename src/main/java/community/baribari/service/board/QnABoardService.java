package community.baribari.service.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.AnswerDto;
import community.baribari.dto.board.QnABoardDto;
import community.baribari.entity.board.Answer;
import community.baribari.entity.board.QnABoard;
import community.baribari.entity.star.QnABoardStar;
import community.baribari.repository.board.AnswerRepository;
import community.baribari.repository.board.QnABoardRepository;
import community.baribari.repository.star.QnABoardStarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class QnABoardService {

    private final QnABoardRepository qnABoardRepository;
    private final AnswerRepository answerRepository;
    private final QnABoardStarRepository qnABoardStarRepository;


    @Transactional
    public void save(QnABoardDto qnABoardDto, PrincipalDetail principalDetail){
        QnABoard qnABoard = QnABoard.toEntity(qnABoardDto, principalDetail);
        QnABoard save = qnABoardRepository.save(qnABoard);

        log.info("{}님이 자유게시물을 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), save.getId());
    }

    public Page<QnABoardDto> list(Pageable pageable){
        Page<QnABoard> qnABoards = qnABoardRepository.findAllByOrderByCreatedAtDesc(pageable);

        return qnABoards.map(QnABoardDto::toDto);
    }

    public List<QnABoardDto> mainList(){
        List<QnABoard> qnABoards = qnABoardRepository.findTop3ByOrderByCreatedAtDesc();

        return qnABoards.stream().map(QnABoardDto::toDto).toList();
    }

    public QnABoardDto detail(Long id) {
        QnABoard qnABoard = qnABoardRepository.findById(id).orElse(null);
        List<AnswerDto> answers = answerRepository.findByQnaBoardId(id)
                .stream()
                .map(AnswerDto::toDto)
                .toList();

        return QnABoardDto.toDto(qnABoard, answers);
    }

    @Transactional
    public void viewCountUp(Long id) {
        QnABoard qnABoard = qnABoardRepository.findById(id).orElse(null);
        qnABoardRepository.save(qnABoard.updateViewCount());
    }

    @Transactional
    public void writeAnswer(Long questionId, PrincipalDetail principalDetail, AnswerDto answerDto) {
        QnABoard qnABoard = qnABoardRepository.findById(questionId).orElse(null);
        Answer answer = Answer.toEntity(answerDto, principalDetail.getMember(), qnABoard);

        answerRepository.save(answer);
    }

    @Transactional
    public void starCountUp(Long id, PrincipalDetail principalDetail) {

        if (qnABoardStarRepository.existsByMemberId(principalDetail.getMember().getId()))
            throw new IllegalArgumentException("이미 추천한 게시물");

        QnABoard qnABoard = qnABoardRepository.findById(id).orElse(null);
        QnABoardStar qnABoardStar = QnABoardStar.builder()
                .member(principalDetail.getMember())
                .qnaBoard(qnABoard)
                .build();

        qnABoardStarRepository.save(qnABoardStar);
    }
}
