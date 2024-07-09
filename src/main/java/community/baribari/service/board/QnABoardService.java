package community.baribari.service.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.AnswerDto;
import community.baribari.dto.board.QnABoardDto;
import community.baribari.entity.board.Answer;
import community.baribari.entity.board.QnABoard;
import community.baribari.entity.star.Star;
import community.baribari.repository.board.AnswerRepository;
import community.baribari.repository.board.QnABoardRepository;
import community.baribari.repository.star.StarRepository;
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
    private final StarRepository starRepository;
    private final AnswerRepository answerRepository;

    public Page<QnABoardDto> list(Pageable pageable){
        Page<QnABoard> qnABoards = qnABoardRepository.findAllByOrderByCreatedAtDesc(pageable);

        return qnABoards.map(QnABoardDto::toDto);
    }

    @Transactional
    public void save(QnABoardDto qnABoardDto, PrincipalDetail principalDetail) {
        QnABoard qnABoard = QnABoard.toEntity(qnABoardDto, principalDetail);
        qnABoardRepository.save(qnABoard);
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
    public void writeAnswer(Long questionId, PrincipalDetail principalDetail, AnswerDto answerDto) {
        QnABoard qnABoard = qnABoardRepository.findById(questionId).orElse(null);
        Answer answer = Answer.toEntity(answerDto, principalDetail.getMember(), qnABoard);

        answerRepository.save(answer);
    }

    @Transactional
    public void viewCountUp(Long id) {
        QnABoard qnABoard = qnABoardRepository.findById(id).orElse(null);
        qnABoard.updateViewCount();
        qnABoardRepository.save(qnABoard);
    }

    @Transactional
    public void starCountUp(Long id, PrincipalDetail principalDetail) {

        if (starRepository.existsByMemberIdAndBoardId(principalDetail.getMember().getId(), id))
            throw new IllegalArgumentException("이미 추천한 게시물");

        QnABoard qnABoard = qnABoardRepository.findById(id).orElse(null);
        Star star = Star.builder()
                .member(principalDetail.getMember())
                .board(qnABoard)
                .build();

        starRepository.save(star);
    }
}
