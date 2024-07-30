package community.baribari.dto.search;

import community.baribari.dto.board.*;
import community.baribari.dto.comment.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDto {

    private Page<FreeBoardDto> freeBoards;
    private Page<QnABoardDto> qnaBoards;
    private Page<BariRecruitDto> recruits;
    private Page<BariReviewDto> reviews;
    private Page<AnswerDto> answers;
    private Page<CommentDto> comments;
}
