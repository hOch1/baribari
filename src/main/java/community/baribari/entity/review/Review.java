package community.baribari.entity.review;

import community.baribari.entity.bari.BariRecruit;
import community.baribari.entity.bari.BariReview;
import community.baribari.entity.board.FreeBoard;
import community.baribari.entity.board.QnABoard;
import community.baribari.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Builder.Default
    private Long starCount = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    private FreeBoard freeBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    private QnABoard qnaBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    private BariRecruit bariRecruit;

    @ManyToOne(fetch = FetchType.LAZY)
    private BariReview bariReview;
}
