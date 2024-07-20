package community.baribari.service.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.AnswerDto;
import community.baribari.dto.board.FreeBoardDto;
import community.baribari.dto.board.QnABoardDto;
import community.baribari.entity.board.Answer;
import community.baribari.entity.board.FreeBoard;
import community.baribari.entity.board.QnABoard;
import community.baribari.exception.BoardNotFoundException;
import community.baribari.repository.board.AnswerRepository;
import community.baribari.repository.board.QnABoardRepository;
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

    public Page<QnABoardDto> list(Pageable pageable){
        Page<QnABoard> qnABoards = qnABoardRepository.findAllByOrderByCreatedAtDesc(pageable);

        return qnABoards.map(QnABoardDto::toDto);
    }

    @Transactional
    public void save(QnABoardDto qnABoardDto, PrincipalDetail principalDetail) {
        QnABoard qnABoard = QnABoard.toEntity(qnABoardDto, principalDetail);
        QnABoard save = qnABoardRepository.save(qnABoard);

        log.info("{}님이 질문을 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), save.getId());
    }

    public List<QnABoardDto> mainList(){
        List<QnABoard> qnABoards = qnABoardRepository.findTop3ByOrderByCreatedAtDesc();

        return qnABoards.stream().map(QnABoardDto::toDto).toList();
    }

    public QnABoardDto detail(Long id) {
        QnABoard qnABoard = qnABoardRepository.findById(id).orElseThrow(BoardNotFoundException::new);

        List<AnswerDto> answers = answerRepository.findByQnaBoardId(id)
                .stream()
                .map(AnswerDto::toDto)
                .toList();

        return QnABoardDto.toDto(qnABoard, answers);
    }

    @Transactional
    public void writeAnswer(Long questionId, PrincipalDetail principalDetail, AnswerDto answerDto) {
        QnABoard qnABoard = qnABoardRepository.findById(questionId).orElseThrow(BoardNotFoundException::new);

        Answer answer = Answer.toEntity(answerDto, principalDetail.getMember(), qnABoard);

        Answer save = answerRepository.save(answer);
        log.info("{}님이 {}에 답변을 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), qnABoard.getId(), save.getId());
    }

    @Transactional
    public void viewCountUp(Long id) {
        QnABoard qnABoard = qnABoardRepository.findById(id).orElseThrow(BoardNotFoundException::new);

        qnABoard.updateViewCount();
        qnABoardRepository.save(qnABoard);
    }

    @Transactional
    public void update(QnABoardDto qnABoardDto) {
        QnABoard qnABoard = qnABoardRepository.findById(qnABoardDto.getId())
                .orElseThrow(BoardNotFoundException::new);

        qnABoard.update(qnABoardDto);
        qnABoardRepository.save(qnABoard);

        log.info("{}님이 게시물 {}을 수정하였습니다.", qnABoard.getMember().getId(), qnABoard.getId());

    }

}
