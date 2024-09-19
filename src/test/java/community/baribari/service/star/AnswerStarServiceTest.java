package community.baribari.service.star;

import community.baribari.config.PrincipalDetail;
import community.baribari.entity.board.Answer;
import community.baribari.entity.member.AccountSetting;
import community.baribari.entity.member.Member;
import community.baribari.entity.star.AnswerStar;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.board.AnswerRepository;
import community.baribari.repository.star.AnswerStarRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnswerStarServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private AnswerStarRepository answerStarRepository;

    @InjectMocks
    private AnswerStarService answerStarService;

    @Test
    @DisplayName("답변_추천")
    void starCountUp() {
        Answer answer = mock(Answer.class);

        AnswerStar answerStar = AnswerStar.builder()
                .answer(answer)
                .build();

        PrincipalDetail principalDetail = new PrincipalDetail(mock(Member.class));

        when(answerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(answer));
        when(answerStarRepository.existsByMemberIdAndAnswerId(anyLong(), anyLong())).thenReturn(false);
        when(answerStarRepository.save(any())).thenReturn(answerStar);

        answerStarService.starCountUp(1L, principalDetail);

        verify(answerStarRepository).save(any());
        verify(answerStarRepository).existsByMemberIdAndAnswerId(anyLong(), anyLong());
        verify(answerRepository).findById(anyLong());
    }

    @Test
    @DisplayName("답변_추천_실패_이미_추천한_답변")
    void starCountUpFail() {
        Answer answer = mock(Answer.class);
        PrincipalDetail principalDetail = new PrincipalDetail(mock(Member.class));

        when(answerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(answer));
        when(answerStarRepository.existsByMemberIdAndAnswerId(anyLong(), anyLong())).thenReturn(true);

        CustomException customException = assertThrows(CustomException.class, () -> {
            answerStarService.starCountUp(1L, principalDetail);
        });

        assertEquals(customException.getErrorCode(), ErrorCode.ALREADY_STARRED_ANSWER);
        verify(answerStarRepository, never()).save(any());
    }
}