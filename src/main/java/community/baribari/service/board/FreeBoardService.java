package community.baribari.service.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.FreeBoardDto;
import community.baribari.entity.board.FreeBoard;
import community.baribari.entity.star.Star;
import community.baribari.repository.board.FreeBoardRepository;
import community.baribari.repository.star.StarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FreeBoardService {

    private final FreeBoardRepository freeBoardRepository;
    private final StarRepository starRepository;

    @Transactional
    public void save(FreeBoardDto freeBoardDto, PrincipalDetail principalDetail){
        FreeBoard freeBoard = FreeBoard.toEntity(freeBoardDto, principalDetail);
        FreeBoard save = freeBoardRepository.save(freeBoard);

        log.info("{}님이 자유게시물을 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), save.getId());
    }

    public Page<FreeBoardDto> list(Pageable pageable){
        Page<FreeBoard> freeBoards = freeBoardRepository.findAllByOrderByCreatedAtDesc(pageable);

        return freeBoards.map(FreeBoardDto::toDto);
    }

    public List<FreeBoardDto> mainList(){
        List<FreeBoard> freeBoards = freeBoardRepository.findTop3ByOrderByCreatedAtDesc();

        return freeBoards.stream().map(FreeBoardDto::toDto).toList();
    }

    public FreeBoardDto detail(Long id) {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElse(null);
        return FreeBoardDto.toDto(freeBoard);
    }

    @Transactional
    public void viewCountUp(Long id) {
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElse(null);
        freeBoard.updateViewCount();
        freeBoardRepository.save(freeBoard);
    }

    @Transactional
    public void starCountUp(Long id, PrincipalDetail principalDetail) {

        if (starRepository.existsByMemberIdAndBoardId(principalDetail.getMember().getId(), id))
            throw new IllegalArgumentException("이미 추천한 게시물");

        FreeBoard freeBoard = freeBoardRepository.findById(id).orElse(null);
        Star star = Star.builder()
                .member(principalDetail.getMember())
                .board(freeBoard)
                .build();

        starRepository.save(star);
    }
}
