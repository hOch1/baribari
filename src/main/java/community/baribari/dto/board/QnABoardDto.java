package community.baribari.dto.board;

import community.baribari.entity.board.Category;
import community.baribari.entity.board.QnABoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QnABoardDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private Long viewCount;
    private Long starCount;
    private Category category;
    private List<AnswerDto> answers;

    public static QnABoardDto toDto(QnABoard qnABoard, List<AnswerDto> answers) {
        return QnABoardDto.builder()
                .id(qnABoard.getId())
                .title(qnABoard.getTitle())
                .content(qnABoard.getContent())
                .writer(qnABoard.getMember().getNickname())
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
                .writer(qnABoard.getMember().getNickname())
                .createdAt(qnABoard.getCreatedAt())
                .viewCount(qnABoard.getViewCount())
                .starCount((long) qnABoard.getBoardStars().size())
                .category(qnABoard.getCategory())
                .build();
    }
}
