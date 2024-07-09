package community.baribari.service.bari;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.bari.BariReviewDto;
import community.baribari.entity.board.BariReview;
import community.baribari.entity.board.Board;
import community.baribari.entity.star.Star;
import community.baribari.repository.board.BariReviewRepository;
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
public class BariReviewService {

    private final BariReviewRepository bariReviewRepository;
    private final StarRepository starRepository;

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
        Board bariReview =  bariReviewRepository.findById(id).get();
        return BariReviewDto.toDto(bariReview);
    }

    @Transactional
    public void viewCountUp(Long id) {
        BariReview review = bariReviewRepository.findById(id).get();
        review.updateViewCount();
        bariReviewRepository.save(review);
    }

    @Transactional
    public void starCountUp(Long id, PrincipalDetail principalDetail) {

        if (starRepository.existsByMemberIdAndBoardId(principalDetail.getMember().getId(), id))
            throw new IllegalArgumentException("이미 추천한 게시물");

        BariReview bariReview = bariReviewRepository.findById(id).orElse(null);
        Star bariReviewStar = Star.builder()
                .member(principalDetail.getMember())
                .board(bariReview)
                .build();

        starRepository.save(bariReviewStar);
    }
}
