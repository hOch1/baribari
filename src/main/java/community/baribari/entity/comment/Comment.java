package community.baribari.entity.comment;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.comment.CommentDto;
import community.baribari.entity.board.Board;
import community.baribari.entity.member.Member;
import community.baribari.entity.star.CommentStar;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @OneToMany(mappedBy = "comment")
    @Builder.Default
    private List<CommentStar> stars = new ArrayList<>();

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private Boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> children = new ArrayList<>();

    public static Comment toEntity(CommentDto commentDto, PrincipalDetail principalDetail, Board board) {
        return Comment.builder()
                .content(commentDto.getContent())
                .member(principalDetail.getMember())
                .board(board)
                .build();
    }

    public static Comment toEntity(CommentDto commentDto, PrincipalDetail principalDetail, Board board, Comment comment) {
        return Comment.builder()
                .content(commentDto.getContent())
                .member(principalDetail.getMember())
                .board(board)
                .parent(comment)
                .build();
    }

    public void delete(){
        this.deleted = true;
    }
}
