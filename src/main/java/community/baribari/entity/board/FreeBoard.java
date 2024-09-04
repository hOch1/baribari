package community.baribari.entity.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.FreeBoardDto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("FREE")
public class FreeBoard extends Board{

    public static FreeBoard toEntity(FreeBoardDto freeBoardDto, PrincipalDetail principalDetail){
        return FreeBoard.builder()
                .id(freeBoardDto.getId())
                .title(freeBoardDto.getTitle())
                .content(freeBoardDto.getContent())
                .category(Category.FREE)
                .member(principalDetail.getMember())
                .build();
    }

    public void update(FreeBoardDto freeBoardDto) {
        this.update(freeBoardDto.getTitle(), freeBoardDto.getContent());
    }
}
