package community.baribari.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 1856138864L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final ListPath<community.baribari.entity.star.BoardStar, community.baribari.entity.star.QBoardStar> boardStars = this.<community.baribari.entity.star.BoardStar, community.baribari.entity.star.QBoardStar>createList("boardStars", community.baribari.entity.star.BoardStar.class, community.baribari.entity.star.QBoardStar.class, PathInits.DIRECT2);

    public final EnumPath<Category> category = createEnum("category", Category.class);

    public final ListPath<community.baribari.entity.comment.Comment, community.baribari.entity.comment.QComment> comments = this.<community.baribari.entity.comment.Comment, community.baribari.entity.comment.QComment>createList("comments", community.baribari.entity.comment.Comment.class, community.baribari.entity.comment.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final BooleanPath deleted = createBoolean("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final community.baribari.entity.member.QMember member;

    public final StringPath title = createString("title");

    public final NumberPath<Long> viewCount = createNumber("viewCount", Long.class);

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new community.baribari.entity.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

