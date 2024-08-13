package community.baribari.dto.report;

import community.baribari.dto.member.MemberDto;
import community.baribari.entity.report.CommentReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentReportDto extends ReportDto {

    private Long commentId;

    public static CommentReportDto toDto(CommentReport report) {
        return CommentReportDto.builder()
                .id(report.getId())
                .content(report.getContent())
                .createdAt(report.getCreatedAt())
                .isResolved(report.isResolved())
                .commentId(report.getComment().getId())
                .member(MemberDto.toDto(report.getMember()))
                .build();
    }
}
