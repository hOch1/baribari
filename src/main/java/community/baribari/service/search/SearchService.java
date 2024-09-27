package community.baribari.service.search;

import community.baribari.dto.board.*;
import community.baribari.dto.comment.CommentDto;
import community.baribari.dto.search.SearchDto;
import community.baribari.dto.search.SearchRequest;
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

    public SearchDto searchAll(SearchRequest searchRequest, Pageable pageable) {
        Page<BariRecruitDto> bariRecruitDtos = bariRecruitService.search(searchRequest, pageable);
        Page<BariReviewDto> bariReviewDtos = bariReviewService.search(searchRequest, pageable);
        Page<FreeBoardDto> freeBoardDtos = freeBoardService.search(searchRequest, pageable);
        Page<QnABoardDto> qnABoardDtos = qnABoardService.search(searchRequest, pageable);
        Page<AnswerDto> answerDtos = answerService.search(searchRequest, pageable);
        Page<CommentDto> commentDtos = commentService.search(searchRequest, pageable);

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
