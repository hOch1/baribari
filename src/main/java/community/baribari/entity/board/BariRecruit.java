package community.baribari.entity.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.BariRecruitDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@DiscriminatorValue("RECRUIT")
public class BariRecruit extends Board {

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BariStatus status = BariStatus.RECRUITING;

    @Enumerated(EnumType.STRING)
    private BariRegion region;

    public static BariRecruit toEntity(BariRecruitDto bariRecruitDto, PrincipalDetail principalDetail){
        BariRegion region = BariRegion.fromDisplayName(bariRecruitDto.getRegion());
        bariRecruitDto.setTitle("["+region.getName()+"] "+bariRecruitDto.getTitle());

        return BariRecruit.builder()
                .id(bariRecruitDto.getId())
                .title(bariRecruitDto.getTitle())
                .content(bariRecruitDto.getContent())
                .member(principalDetail.getMember())
                .category(Category.RECRUIT)
                .status(BariStatus.RECRUITING)
                .region(region)
                .build();
    }

    public void update(BariRecruitDto bariRecruitDto) {
        BariRegion region = BariRegion.fromDisplayName(bariRecruitDto.getRegion());
        bariRecruitDto.setTitle("["+region.getName()+"] "+bariRecruitDto.getTitle());

        this.region = region;
        this.update(bariRecruitDto.getTitle(), bariRecruitDto.getContent());
    }
}
