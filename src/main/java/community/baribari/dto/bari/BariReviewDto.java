package community.baribari.dto.bari;

import community.baribari.entity.bari.BariRecruit;
import community.baribari.entity.bari.BariRegion;
import community.baribari.entity.bari.BariReview;
import community.baribari.entity.bari.BariStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BariReviewDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String writer;
    private Long starCount;
    private Long viewCount;

    public static BariReviewDto toDto(BariReview bariReview) {
        return BariReviewDto.builder()
                .id(bariReview.getId())
                .title(bariReview.getTitle())
                .content(bariReview.getContent())
                .createdAt(bariReview.getCreatedAt())
                .writer(bariReview.getMember().getNickname())
                .starCount(bariReview.getStarCount())
                .viewCount(bariReview.getViewCount())
                .build();
    }
}
