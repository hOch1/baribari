package community.baribari.service.star;

import community.baribari.config.PrincipalDetail;
import community.baribari.entity.comment.Comment;
import community.baribari.entity.star.CommentStar;
import community.baribari.exception.BoardNotFoundException;
import community.baribari.exception.CommentNotFoundException;
import community.baribari.repository.board.CommentRepository;
import community.baribari.repository.star.CommentStarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CommentStarService {

    private final CommentRepository commentRepository;
    private final CommentStarRepository commentStarRepository;

    @Transactional
    public void starCountUp(Long id, PrincipalDetail principalDetail) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);

        if (commentStarRepository.existsByMemberIdAndCommentId(principalDetail.getMember().getId(), id))
            throw new IllegalArgumentException("이미 추천한 댓글입니다.");

        CommentStar star = CommentStar.builder()
                .comment(comment)
                .member(principalDetail.getMember())
                .build();

        commentStarRepository.save(star);
    }
}
