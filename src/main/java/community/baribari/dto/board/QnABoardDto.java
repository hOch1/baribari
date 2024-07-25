package community.baribari.dto.board;

import community.baribari.dto.member.MemberDto;
import community.baribari.entity.board.QnABoard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QnABoardDto extends BoardDto{

    private List<AnswerDto> answers;

    public static QnABoardDto toDto(QnABoard qnABoard, List<AnswerDto> answers) {
        return QnABoardDto.builder()
                .id(qnABoard.getId())
                .title(qnABoard.getTitle())
                .content(qnABoard.getContent())
                .member(MemberDto.toDto(qnABoard.getMember()))
                .createdAt(qnABoard.getCreatedAt())
                .viewCount(qnABoard.getViewCount())
                .starCount((long) qnABoard.getBoardStars().size())
                .category(qnABoard.getCategory())
                .answers(answers)
                .build();
    }

    public static QnABoardDto toDto(QnABoard qnABoard) {
        return QnABoardDto.builder()
                .id(qnABoard.getId())
                .title(qnABoard.getTitle())
                .content(qnABoard.getContent())
                .member(MemberDto.toDto(qnABoard.getMember()))
                .createdAt(qnABoard.getCreatedAt())
                .viewCount(qnABoard.getViewCount())
                .starCount((long) qnABoard.getBoardStars().size())
                .category(qnABoard.getCategory())
                .answers(qnABoard.getAnswers().stream().map(AnswerDto::toDto).toList())
                .build();
    }
}
