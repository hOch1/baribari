package community.baribari.dto.board;

import community.baribari.dto.MemberDto;
import community.baribari.entity.board.BariReview;
import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@AllArgsConstructor
public class BariReviewDto extends BoardDto {

    public static BariReviewDto toDto(BariReview bariReview) {
        return BariReviewDto.builder()
                .id(bariReview.getId())
                .title(bariReview.getTitle())
                .content(bariReview.getContent())
                .createdAt(bariReview.getCreatedAt())
                .member(MemberDto.toDto(bariReview.getMember()))
                .starCount((long) bariReview.getBoardStars().size())
                .viewCount(bariReview.getViewCount())
                .category(bariReview.getCategory())
                .build();
    }
}
