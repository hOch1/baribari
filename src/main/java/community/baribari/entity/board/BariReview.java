package community.baribari.entity.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.BariReviewDto;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
public class BariReview extends Board{

    public static BariReview toEntity(BariReviewDto bariReviewDto, PrincipalDetail principalDetail){
        return BariReview.builder()
                .id(bariReviewDto.getId())
                .title(bariReviewDto.getTitle())
                .content(bariReviewDto.getContent())
                .category(Category.REVIEW)
                .member(principalDetail.getMember())
                .build();
    }

    public void update(BariReviewDto bariReviewDto) {
        this.update(bariReviewDto.getTitle(), bariReviewDto.getContent());
    }
}
