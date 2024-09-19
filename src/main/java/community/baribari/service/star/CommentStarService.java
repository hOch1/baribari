package community.baribari.service.star;

import community.baribari.config.PrincipalDetail;
import community.baribari.entity.comment.Comment;
import community.baribari.entity.star.CommentStar;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.repository.comment.CommentRepository;
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
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if (commentStarRepository.existsByMemberIdAndCommentId(principalDetail.getMember().getId(), id))
            throw new CustomException(ErrorCode.ALREADY_STARRED_COMMENT);

        CommentStar star = CommentStar.toEntity(principalDetail.getMember(), comment);
        CommentStar save = commentStarRepository.save(star);

        log.info("{}님이 댓글 {}을 추천했습니다. ID : {}", principalDetail.getMember().getNickname(), comment.getId(), save.getId());
    }
}
