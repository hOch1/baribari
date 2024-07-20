package community.baribari.dto.board;

import community.baribari.dto.MemberDto;
import community.baribari.entity.board.Board;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
public class FreeBoardDto extends BoardDto{

    public static FreeBoardDto toDto(Board board) {
        return FreeBoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .member(MemberDto.toDto(board.getMember()))
                .createdAt(board.getCreatedAt())
                .viewCount(board.getViewCount())
                .starCount((long) board.getBoardStars().size())
                .category(board.getCategory())
                .build();
    }
}