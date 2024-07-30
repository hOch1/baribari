package community.baribari.dto.board;


import community.baribari.dto.member.MemberDto;
import community.baribari.entity.board.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BoardDto {

    private Long id;

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(min = 2, message = "제목은 2자 이상 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    @Size(min = 5, message = "내용은 5자 이상 입력해주세요.")
    private String content;

    private LocalDateTime createdAt;
    private MemberDto member;
    private Long starCount;
    private Long viewCount;
    private Category category;
}
