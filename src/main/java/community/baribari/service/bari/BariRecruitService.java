package community.baribari.service.bari;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.bari.BariRecruitDto;
import community.baribari.entity.board.BariRecruit;
import community.baribari.entity.star.Star;
import community.baribari.repository.board.BariRecruitRepository;
import community.baribari.repository.star.StarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BariRecruitService {

    private final BariRecruitRepository bariRecruitRepository;
    private final StarRepository starRepository;

    @Transactional
    public void save(BariRecruitDto boardDto, PrincipalDetail principalDetail){
        BariRecruit bariRecruit = BariRecruit.toEntity(boardDto, principalDetail);
        BariRecruit save = bariRecruitRepository.save(bariRecruit);

        log.info("{}님이 바리 모집을 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), save.getId());
    }

    public Page<BariRecruitDto> list(Pageable pageable){
        Page<BariRecruit> recruits = bariRecruitRepository.findAllByOrderByCreatedAtDesc(pageable);

        return recruits.map(BariRecruitDto::toDto);
    }

    public List<BariRecruitDto> mainList(){
        List<BariRecruit> recruits = bariRecruitRepository.findTop3ByOrderByCreatedAtDesc();

        return recruits.stream().map(BariRecruitDto::toDto).toList();
    }

    public BariRecruitDto detail(Long id){
        BariRecruit bariRecruit = bariRecruitRepository.findById(id).orElse(null);
        return BariRecruitDto.toDto(bariRecruit);
    }

    @Transactional
    public void viewCountUp(Long id) {
        BariRecruit bariRecruit = bariRecruitRepository.findById(id).get();
        bariRecruit.updateViewCount();
        bariRecruitRepository.save(bariRecruit);
    }

    @Transactional
    public void starCountUp(Long id, PrincipalDetail principalDetail) {

        if (starRepository.existsByMemberIdAndBoardId(principalDetail.getMember().getId(), id))
            throw new IllegalArgumentException("이미 추천한 게시물");

        BariRecruit bariRecruit = bariRecruitRepository.findById(id).orElse(null);
        Star bariRecruitStar = Star.builder()
                .member(principalDetail.getMember())
                .board(bariRecruit)
                .build();

        starRepository.save(bariRecruitStar);
    }
}
