package community.baribari.service.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.BariReviewDto;
import community.baribari.entity.board.BariReview;
import community.baribari.entity.board.Board;
import community.baribari.exception.BoardNotFoundException;
import community.baribari.repository.board.BariReviewRepository;
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
public class BariReviewService {

    private final BariReviewRepository bariReviewRepository;

    @Transactional
    public void save(BariReviewDto bariReviewDto, PrincipalDetail principalDetail){
        BariReview review = BariReview.toEntity(bariReviewDto, principalDetail);

        BariReview save = bariReviewRepository.save(review);
        log.info("{}님이 바리 후기를 등록했습니다. ID : {}", principalDetail.getMember().getNickname(), save.getId());
    }

    public Page<BariReviewDto> list(Pageable pageable){
        Page<BariReview> reviews = bariReviewRepository.findAllByOrderByCreatedAtDesc(pageable);

        return reviews.map(BariReviewDto::toDto);
    }

    public List<BariReviewDto> mainList(){
        List<BariReview> reviews = bariReviewRepository.findTop3ByOrderByCreatedAtDesc();

        return reviews.stream().map(BariReviewDto::toDto).toList();
    }

    public BariReviewDto detail(Long id){
        Board bariReview =  bariReviewRepository.findById(id).orElseThrow(BoardNotFoundException::new);

        return BariReviewDto.toDto(bariReview);
    }

    @Transactional
    public void viewCountUp(Long id) {
        BariReview review = bariReviewRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        review.updateViewCount();
        bariReviewRepository.save(review);
    }

}
