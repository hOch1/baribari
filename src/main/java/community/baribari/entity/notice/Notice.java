package community.baribari.entity.notice;

import community.baribari.dto.notice.NoticeDto;
import community.baribari.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    public static Notice toEntity(Member member, NoticeDto noticeDto) {
        return Notice.builder()
                .title(noticeDto.getTitle())
                .content(noticeDto.getContent())
                .member(member)
                .build();
    }
}
