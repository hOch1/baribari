package community.baribari.entity.board;

import community.baribari.dto.board.AnswerDto;
import community.baribari.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Answer extends Board{

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
