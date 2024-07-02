package community.baribari.entity.bari;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.bari.BariRecruitDto;
import community.baribari.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BariRecruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Builder.Default
    private Long viewCount = 0L;

    @Builder.Default
    private Long starCount = 0L;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BariStatus status = BariStatus.RECRUITING;

    public BariRecruit updateViewCount() {
        this.viewCount++;
        return this;
    }

    public static BariRecruit toEntity(BariRecruitDto bariRecruitDto, PrincipalDetail principalDetail){
        return BariRecruit.builder()
                .title(bariRecruitDto.getTitle())
                .content(bariRecruitDto.getContent())
                .member(principalDetail.getMember())
                .build();
    }
}
