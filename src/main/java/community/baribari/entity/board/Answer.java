package community.baribari.entity.board;

import community.baribari.dto.board.AnswerDto;
import community.baribari.entity.member.Member;
import community.baribari.entity.star.AnswerStar;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @OneToMany(mappedBy = "answer")
    private List<AnswerStar> stars = new ArrayList<>();

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private QnABoard qnaBoard;

    public static Answer toEntity(AnswerDto answerDto, Member member, QnABoard qnaBoard){
        return Answer.builder()
                .content(answerDto.getContent())
                .member(member)
                .qnaBoard(qnaBoard)
                .build();
    }
}
