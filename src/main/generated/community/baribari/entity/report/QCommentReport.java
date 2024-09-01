package community.baribari.entity.report;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentReport is a Querydsl query type for CommentReport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentReport extends EntityPathBase<CommentReport> {

    private static final long serialVersionUID = 1679364263L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentReport commentReport = new QCommentReport("commentReport");

    public final QReport _super;

    public final community.baribari.entity.comment.QComment comment;

    //inherited
    public final StringPath content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final BooleanPath isResolved;

    // inherited
    public final community.baribari.entity.member.QMember member;

    public QCommentReport(String variable) {
        this(CommentReport.class, forVariable(variable), INITS);
    }

    public QCommentReport(Path<? extends CommentReport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentReport(PathMetadata metadata, PathInits inits) {
        this(CommentReport.class, metadata, inits);
    }

    public QCommentReport(Class<? extends CommentReport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QReport(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new community.baribari.entity.comment.QComment(forProperty("comment"), inits.get("comment")) : null;
        this.content = _super.content;
        this.createdAt = _super.createdAt;
        this.id = _super.id;
        this.isResolved = _super.isResolved;
        this.member = _super.member;
    }

}

