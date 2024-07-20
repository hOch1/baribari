package community.baribari.dto.board;

import community.baribari.dto.MemberDto;
import community.baribari.entity.board.BariRecruit;
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
    private MemberDto member;
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
                .member(MemberDto.toDto(bariRecruit.getMember()))
                .starCount((long) bariRecruit.getBoardStars().size())
                .viewCount(bariRecruit.getViewCount())
                .status(bariRecruit.getStatus().getName())
                .region(bariRecruit.getRegion().getName())
                .build();
    }
}
