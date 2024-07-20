package community.baribari.entity.board;


import community.baribari.entity.comment.Comment;
import community.baribari.entity.member.Member;
import community.baribari.entity.star.BoardStar;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Builder.Default
    private Long viewCount = 0L;

    @OneToMany(mappedBy = "board")
    @Builder.Default
    private List<BoardStar> boardStars = new ArrayList<>();

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "board")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();


    public void updateViewCount() {
        this.viewCount++;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    protected Board(String title, String content, Member member, Category category) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.category = category;
        this.viewCount = 0L;
        this.boardStars = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }
}
