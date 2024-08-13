package community.baribari.dto.report;

import community.baribari.dto.member.MemberDto;
import community.baribari.entity.report.BoardReport;
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
public class BoardReportDto extends ReportDto {

    private Long boardId;

    public static BoardReportDto toDto(BoardReport report) {
        return BoardReportDto.builder()
                .id(report.getId())
                .content(report.getContent())
                .createdAt(report.getCreatedAt())
                .isResolved(report.isResolved())
                .boardId(report.getBoard().getId())
                .member(MemberDto.toDto(report.getMember()))
                .build();
    }
}
