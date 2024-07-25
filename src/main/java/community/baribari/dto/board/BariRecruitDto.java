package community.baribari.dto.board;

import community.baribari.dto.member.MemberDto;
import community.baribari.entity.board.BariRecruit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class BariRecruitDto extends BoardDto{

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
