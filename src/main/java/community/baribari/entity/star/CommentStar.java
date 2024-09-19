package community.baribari.entity.star;

import community.baribari.entity.comment.Comment;
import community.baribari.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentStar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    public static CommentStar toEntity(Member member, Comment comment){
        return CommentStar.builder()
                .member(member)
                .comment(comment)
                .build();
    }
}
