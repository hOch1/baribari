package community.baribari.dto.notice;

import community.baribari.dto.member.MemberDto;
import community.baribari.entity.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private MemberDto member;

    public static NoticeDto toDto(Notice notice){
        return NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt())
                .member(MemberDto.toDto(notice.getMember()))
                .build();
    }
}
