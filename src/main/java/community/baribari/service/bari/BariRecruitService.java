package community.baribari.service.bari;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.bari.BariRecruitDto;
import community.baribari.entity.bari.BariRecruit;
import community.baribari.repository.BariRecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BariRecruitService {

    private final BariRecruitRepository bariRecruitRepository;

    @Transactional
    public void save(BariRecruitDto recruitDto, PrincipalDetail principalDetail){
        BariRecruit bariRecruit = BariRecruit.toEntity(recruitDto, principalDetail);
        BariRecruit save = bariRecruitRepository.save(bariRecruit);

        log.info("{}님이 바리 모집을 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), save.getId());
    }

    public List<BariRecruitDto> list(){
        List<BariRecruit> recruits = bariRecruitRepository.findAll();
        List<BariRecruitDto> dtos = new ArrayList<>();

        for (BariRecruit recruit : recruits)
            dtos.add(BariRecruitDto.toDto(recruit));

        return dtos;
    }

    public BariRecruitDto detail(Long id){
        BariRecruit bariRecruit = bariRecruitRepository.findById(id).orElse(null);
        return BariRecruitDto.toDto(bariRecruit);
    }

    @Transactional
    public void viewCountUp(Long id) {
        BariRecruit bariRecruit = bariRecruitRepository.findById(id).get();
        bariRecruitRepository.save(bariRecruit.updateViewCount());
    }
}
