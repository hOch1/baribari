package community.baribari.entity.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.QnABoardDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class QnABoard extends Board{

    @OneToMany(mappedBy = "qnaBoard")
    private List<Answer> answers;

    public static QnABoard toEntity(QnABoardDto qnABoardDto, PrincipalDetail principalDetail) {
        return QnABoard.builder()
                .title(qnABoardDto.getTitle())
                .content(qnABoardDto.getContent())
                .member(principalDetail.getMember())
                .category(Category.QNA)
                .build();
    }
}
