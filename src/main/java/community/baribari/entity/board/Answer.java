package community.baribari.entity.board;

import community.baribari.dto.board.AnswerDto;
import community.baribari.entity.member.Member;
import community.baribari.entity.star.AnswerStar;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Answer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "answer")
    @Builder.Default
    private List<AnswerStar> answerStars = new ArrayList<>();

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
