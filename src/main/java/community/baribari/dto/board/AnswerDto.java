package community.baribari.dto.board;

import community.baribari.dto.member.MemberDto;
import community.baribari.entity.board.Answer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "내용을 입력해주세요.")
    @Size(min = 2, message = "내용은 2자 이상 입력해주세요.")
    private String content;

    private Long starCount;
    private LocalDateTime createdAt;
    private MemberDto member;
    private Long questionId;

    public static AnswerDto toDto(Answer answer){
        return AnswerDto.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .starCount((long) answer.getAnswerStars().size())
                .createdAt(answer.getCreatedAt())
                .member(MemberDto.toDto(answer.getMember()))
                .questionId(answer.getQnaBoard().getId())
                .build();
    }

}
