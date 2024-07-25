package community.baribari.dto.comment;

import community.baribari.dto.member.MemberDto;
import community.baribari.entity.comment.Comment;
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
