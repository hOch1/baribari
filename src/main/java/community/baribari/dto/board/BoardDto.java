package community.baribari.dto.board;


import community.baribari.dto.member.MemberDto;
import community.baribari.entity.board.Category;
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
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private MemberDto member;
    private Long starCount;
    private Long viewCount;
    private Category category;
}
