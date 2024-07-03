package community.baribari.service.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.FreeBoardDto;
import community.baribari.dto.board.QnABoardDto;
import community.baribari.entity.board.QnABoard;
import community.baribari.repository.QnABoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class QnABoardService {

    private final QnABoardRepository qnABoardRepository;


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
        return QnABoardDto.toDto(qnABoard);
    }

    @Transactional
    public void viewCountUp(Long id) {
        QnABoard qnABoard = qnABoardRepository.findById(id).orElse(null);
        qnABoardRepository.save(qnABoard.updateViewCount());
    }
}
