package community.baribari.entity.report;

import community.baribari.dto.report.BoardReportDto;
import community.baribari.entity.board.Board;
import community.baribari.entity.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardReport extends Report{

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public static BoardReport toEntity(BoardReportDto boardReportDto, Board board, Member member){
        return BoardReport.builder()
                .content(boardReportDto.getContent())
                .member(member)
                .board(board)
                .build();
    }

}
