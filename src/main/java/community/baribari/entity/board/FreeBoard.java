package community.baribari.entity.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.FreeBoardDto;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
public class FreeBoard extends Board{

    public static FreeBoard toEntity(FreeBoardDto freeBoardDto, PrincipalDetail principalDetail){
        return FreeBoard.builder()
                .title(freeBoardDto.getTitle())
                .content(freeBoardDto.getContent())
                .category(Category.FREE)
                .member(principalDetail.getMember())
                .build();
    }
}
