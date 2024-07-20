package community.baribari.dto.board;

import community.baribari.dto.MemberDto;
import community.baribari.entity.board.Answer;
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
    private MemberDto member;
    private Long questionId;

    public static AnswerDto toDto(Answer answer){
        return AnswerDto.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .starCount((long) answer.getBoardStars().size())
                .createdAt(answer.getCreatedAt())
                .member(MemberDto.toDto(answer.getMember()))
                .questionId(answer.getQnaBoard().getId())
                .build();
    }

}
