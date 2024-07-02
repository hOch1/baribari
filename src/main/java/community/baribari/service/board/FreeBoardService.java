package community.baribari.service.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.FreeBoardDto;
import community.baribari.entity.board.FreeBoard;
import community.baribari.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FreeBoardService {

    private final FreeBoardRepository freeBoardRepository;

    @Transactional
    public void save(FreeBoardDto freeBoardDto, PrincipalDetail principalDetail){
        FreeBoard freeBoard = FreeBoard.toEntity(freeBoardDto, principalDetail);
        FreeBoard save = freeBoardRepository.save(freeBoard);

        log.info("{}님이 자유게시물을 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), save.getId());
    }

    public List<FreeBoardDto> list(){
        List<FreeBoard> freeBoards = freeBoardRepository.findAll();
        List<FreeBoardDto> dtos = new ArrayList<>();

        for (FreeBoard freeBoard : freeBoards)
            dtos.add(FreeBoardDto.toDto(freeBoard));

        return dtos;
    }

    public FreeBoardDto detail(Long id) {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElse(null);
        return FreeBoardDto.toDto(freeBoard);
    }

    @Transactional
    public void viewCountUp(Long id) {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElse(null);
        freeBoardRepository.save(freeBoard.updateViewCount());
    }
}
