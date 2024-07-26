package community.baribari.service.star;

import community.baribari.config.PrincipalDetail;
import community.baribari.entity.comment.Comment;
import community.baribari.entity.star.CommentStar;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
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
    public CommentStar starCountUp(Long id, PrincipalDetail principalDetail) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if (commentStarRepository.existsByMemberIdAndCommentId(principalDetail.getMember().getId(), id))
            throw new CustomException(ErrorCode.ALREADY_STARRED_COMMENT);

        CommentStar star = CommentStar.builder()
                .comment(comment)
                .member(principalDetail.getMember())
                .build();

        return commentStarRepository.save(star);
    }


}
