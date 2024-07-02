package community.baribari.dto.board;

import community.baribari.entity.board.FreeBoard;
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

    public static FreeBoardDto toDto(FreeBoard freeBoard) {
        return FreeBoardDto.builder()
                .id(freeBoard.getId())
                .title(freeBoard.getTitle())
                .content(freeBoard.getContent())
                .writer(freeBoard.getMember().getNickname())
                .createdAt(freeBoard.getCreatedAt())
                .viewCount(freeBoard.getViewCount())
                .starCount(freeBoard.getStarCount())
                .build();
    }
}
