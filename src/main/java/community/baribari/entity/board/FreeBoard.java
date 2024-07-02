package community.baribari.entity.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.FreeBoardDto;
import community.baribari.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FreeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Builder.Default
    private Long viewCount = 0L;

    @Builder.Default
    private Long starCount = 0L;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public static FreeBoard toEntity(FreeBoardDto freeBoardDto, PrincipalDetail principalDetail) {
        return FreeBoard.builder()
                .title(freeBoardDto.getTitle())
                .content(freeBoardDto.getContent())
                .member(principalDetail.getMember())
                .build();
    }

    public FreeBoard updateViewCount() {
        this.viewCount++;
        return this;
    }
}
