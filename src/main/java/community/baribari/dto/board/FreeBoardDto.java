package community.baribari.dto.board;

import community.baribari.entity.board.Board;
import community.baribari.entity.board.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeBoardDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private Long viewCount;
    private Long starCount;
    private Category category;

    public static FreeBoardDto toDto(Board board) {
        return FreeBoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getMember().getNickname())
                .createdAt(board.getCreatedAt())
                .viewCount(board.getViewCount())
                .starCount((long) board.getStars().size())
                .category(board.getCategory())
                .build();
    }
}