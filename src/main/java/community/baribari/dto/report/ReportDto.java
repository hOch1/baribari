package community.baribari.dto.report;

import community.baribari.dto.board.BoardDto;
import community.baribari.dto.comment.CommentDto;
import community.baribari.dto.member.MemberDto;
import community.baribari.entity.report.BoardReport;
import community.baribari.entity.report.CommentReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class ReportDto {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private boolean isResolved;
    private MemberDto member;
}
