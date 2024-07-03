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
public class BariRecruitDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String writer;
    private Long starCount;
    private Long viewCount;
    private String status;
    private String region;

    public static BariRecruitDto toDto(BariRecruit bariRecruit) {
        return BariRecruitDto.builder()
                .id(bariRecruit.getId())
                .title(bariRecruit.getTitle())
                .content(bariRecruit.getContent())
                .createdAt(bariRecruit.getCreatedAt())
                .writer(bariRecruit.getMember().getNickname())
                .starCount(bariRecruit.getStarCount())
                .viewCount(bariRecruit.getViewCount())
                .status(bariRecruit.getStatus().getName())
                .region(bariRecruit.getRegion().getName())
                .build();
    }
}
