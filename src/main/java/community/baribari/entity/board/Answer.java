package community.baribari.entity.board;

import community.baribari.dto.board.AnswerDto;
import community.baribari.entity.member.Member;
import community.baribari.entity.star.AnswerStar;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
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

    @Builder.Default
    private boolean deleted = false;

    @Builder.Default
    private boolean accepted = false;

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

    public Answer delete() {
        if (this.accepted)
            throw new CustomException(ErrorCode.ANSWER_ACCEPTED);

        this.deleted = true;
        return this;
    }

    public void accept() {
        this.accepted = true;
    }

    public Answer update(AnswerDto answerDto) {
        if (this.accepted)
            throw new CustomException(ErrorCode.ANSWER_ACCEPTED);

        this.content = answerDto.getContent();
        return this;
    }
}
