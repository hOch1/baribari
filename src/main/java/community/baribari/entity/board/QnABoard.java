package community.baribari.entity.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.bari.BariReviewDto;
import community.baribari.dto.board.QnABoardDto;
import community.baribari.entity.bari.BariReview;
import community.baribari.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class QnABoard {

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

    @OneToMany(mappedBy = "qnaBoard")
    private List<Answer> answers;

    public QnABoard updateViewCount() {
        this.viewCount++;
        return this;
    }

    public static QnABoard toEntity(QnABoardDto qnABoardDto, PrincipalDetail principalDetail){
        return QnABoard.builder()
                .title(qnABoardDto.getTitle())
                .content(qnABoardDto.getContent())
                .member(principalDetail.getMember())
                .build();
    }
}
