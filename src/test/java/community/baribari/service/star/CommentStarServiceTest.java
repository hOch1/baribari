package community.baribari.service.star;

import community.baribari.config.PrincipalDetail;
import community.baribari.entity.comment.Comment;
import community.baribari.entity.member.Member;
import community.baribari.entity.star.CommentStar;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.comment.CommentRepository;
import community.baribari.repository.star.CommentStarRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentStarServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentStarRepository commentStarRepository;

    @InjectMocks
    private CommentStarService commentStarService;

    @Test
    @DisplayName("댓글_추천")
    void starCountUp() {
        Comment comment = mock(Comment.class);
        CommentStar commentStar = CommentStar.builder()
                .comment(comment)
                .build();

        PrincipalDetail principalDetail = new PrincipalDetail(mock(Member.class));

        when(commentRepository.findById(anyLong())).thenReturn(java.util.Optional.of(comment));
        when(commentStarRepository.existsByMemberIdAndCommentId(anyLong(), anyLong())).thenReturn(false);
        when(commentStarRepository.save(any())).thenReturn(commentStar);

        commentStarService.starCountUp(1L, principalDetail);

        verify(commentStarRepository).save(any());
        verify(commentStarRepository).existsByMemberIdAndCommentId(anyLong(), anyLong());
        verify(commentRepository).findById(anyLong());
    }

    @Test
    @DisplayName("댓글_추천_실패_이미_추천한_댓글")
    void starCountUpFail(){
        Comment comment = mock(Comment.class);
        PrincipalDetail principalDetail = new PrincipalDetail(mock(Member.class));

        when(commentRepository.findById(anyLong())).thenReturn(java.util.Optional.of(comment));
        when(commentStarRepository.existsByMemberIdAndCommentId(anyLong(), anyLong())).thenReturn(true);

        CustomException customException = assertThrows(CustomException.class, () -> {
            commentStarService.starCountUp(1L, principalDetail);
        });

        assertEquals(customException.getErrorCode(), ErrorCode.ALREADY_STARRED_COMMENT);
        verify(commentStarRepository, never()).save(any());
    }
}