package community.baribari.dto.board;

import community.baribari.dto.MemberDto;
import community.baribari.entity.board.Board;
import community.baribari.entity.board.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BariReviewDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private MemberDto member;
    private Long starCount;
    private Long viewCount;
    private Category category;

    public static BariReviewDto toDto(Board board) {
        return BariReviewDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .member(MemberDto.toDto(board.getMember()))
                .starCount((long) board.getBoardStars().size())
                .viewCount(board.getViewCount())
                .category(board.getCategory())
                .build();
    }
}