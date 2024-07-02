package community.baribari.entity.bari;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.bari.BariReviewDto;
import community.baribari.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BariReview {

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

    public BariReview updateViewCount() {
        this.viewCount++;
        return this;
    }

    public static BariReview toEntity(BariReviewDto bariReviewDto, PrincipalDetail principalDetail){
        return BariReview.builder()
                .title(bariReviewDto.getTitle())
                .content(bariReviewDto.getContent())
                .member(principalDetail.getMember())
                .build();
    }
}
