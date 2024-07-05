package community.baribari.entity.star;

import community.baribari.entity.bari.BariRecruit;
import community.baribari.entity.board.FreeBoard;
import community.baribari.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BariRecruitStar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private BariRecruit bariRecruit;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
