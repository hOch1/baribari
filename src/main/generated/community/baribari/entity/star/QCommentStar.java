package community.baribari.entity.star;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentStar is a Querydsl query type for CommentStar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentStar extends EntityPathBase<CommentStar> {

    private static final long serialVersionUID = 1022059043L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentStar commentStar = new QCommentStar("commentStar");

    public final community.baribari.entity.comment.QComment comment;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final community.baribari.entity.member.QMember member;

    public QCommentStar(String variable) {
        this(CommentStar.class, forVariable(variable), INITS);
    }

    public QCommentStar(Path<? extends CommentStar> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentStar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentStar(PathMetadata metadata, PathInits inits) {
        this(CommentStar.class, metadata, inits);
    }

    public QCommentStar(Class<? extends CommentStar> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new community.baribari.entity.comment.QComment(forProperty("comment"), inits.get("comment")) : null;
        this.member = inits.isInitialized("member") ? new community.baribari.entity.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

