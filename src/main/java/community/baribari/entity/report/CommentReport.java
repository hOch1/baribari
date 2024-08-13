package community.baribari.entity.report;

import community.baribari.dto.report.CommentReportDto;
import community.baribari.entity.comment.Comment;
import community.baribari.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReport extends Report{

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    public static CommentReport toEntity(CommentReportDto commentReportDto, Comment comment, Member member){
        return CommentReport.builder()
                .content(commentReportDto.getContent())
                .member(member)
                .comment(comment)
                .build();
    }
}
