package community.baribari.dto.comment;

import community.baribari.entity.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {


    private Long id;
    private String content;
    private Long starCount;
    private LocalDateTime createdAt;
    private String writer;
    private Long boardId;

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .starCount((long) comment.getStars().size())
                .createdAt(comment.getCreatedAt())
                .writer(comment.getMember().getNickname())
                .boardId(comment.getBoard().getId())
                .build();
    }
}
