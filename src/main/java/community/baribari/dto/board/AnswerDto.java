package community.baribari.dto.board;

import community.baribari.entity.board.Answer;
import community.baribari.entity.board.QnABoard;
import community.baribari.entity.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

    private Long id;
    private String content;
    private Long starCount;
    private LocalDateTime createdAt;
    private String writer;
    private Long questionId;

    public static AnswerDto toDto(Answer answer){
        return AnswerDto.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .starCount((long) answer.getStars().size())
                .createdAt(answer.getCreatedAt())
                .writer(answer.getMember().getNickname())
                .questionId(answer.getQnaBoard().getId())
                .build();
    }

}
