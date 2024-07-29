package community.baribari.service.search;

import community.baribari.dto.board.*;
import community.baribari.dto.comment.CommentDto;
import community.baribari.dto.search.SearchDto;
import community.baribari.service.board.AnswerService;
import community.baribari.service.board.extend.BariRecruitService;
import community.baribari.service.board.extend.BariReviewService;
import community.baribari.service.board.extend.FreeBoardService;
import community.baribari.service.board.extend.QnABoardService;
import community.baribari.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final BariRecruitService bariRecruitService;
    private final BariReviewService bariReviewService;
    private final FreeBoardService freeBoardService;
    private final QnABoardService qnABoardService;
    private final AnswerService answerService;
    private final CommentService commentService;

    public SearchDto searchAll(String keyword, Pageable pageable) {
        Page<BariRecruitDto> bariRecruitDtos = bariRecruitService.search(keyword, pageable);
        Page<BariReviewDto> bariReviewDtos = bariReviewService.search(keyword, pageable);
        Page<FreeBoardDto> freeBoardDtos = freeBoardService.search(keyword, pageable);
        Page<QnABoardDto> qnABoardDtos = qnABoardService.search(keyword, pageable);
        Page<AnswerDto> answerDtos = answerService.search(keyword, pageable);
        Page<CommentDto> commentDtos = commentService.search(keyword, pageable);

        return SearchDto.builder()
                .freeBoards(freeBoardDtos)
                .qnaBoards(qnABoardDtos)
                .recruits(bariRecruitDtos)
                .reviews(bariReviewDtos)
                .answers(answerDtos)
                .comments(commentDtos)
                .build();
    }
}
