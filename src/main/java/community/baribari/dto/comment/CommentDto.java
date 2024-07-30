package community.baribari.dto.comment;

import community.baribari.dto.member.MemberDto;
import community.baribari.entity.comment.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {


    private Long id;

    @NotBlank(message = "내용을 입력해주세요.")
    @Size(min = 2, message = "내용은 2자 이상 입력해주세요.")
    private String content;

    private Long starCount;
    private LocalDateTime createdAt;
    private MemberDto member;
    private Long boardId;
    private boolean deleted;
    private List<CommentDto> children;

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .starCount((long) comment.getStars().size())
                .createdAt(comment.getCreatedAt())
                .member(MemberDto.toDto(comment.getMember()))
                .boardId(comment.getBoard().getId())
                .deleted(comment.getDeleted())
                .children(comment.getChildren().stream().map(CommentDto::toDto).toList())
                .build();
    }
}
