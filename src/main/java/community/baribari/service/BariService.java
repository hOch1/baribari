package community.baribari.service;

import community.baribari.config.PrincipalDetail;
import community.baribari.entity.bari.BariReview;
import community.baribari.repository.BariReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BariService {

    private final BariReviewRepository reviewRepository;

    @Transactional
    public void save(String title, String content, PrincipalDetail principalDetail){


        BariReview review = BariReview.builder()
                .title(title)
                .content(content)
                .member(principalDetail.getMember())
                .build();

        reviewRepository.save(review);
    }
}
