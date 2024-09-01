package community.baribari.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFreeBoard is a Querydsl query type for FreeBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoard extends EntityPathBase<FreeBoard> {

    private static final long serialVersionUID = -1504070044L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFreeBoard freeBoard = new QFreeBoard("freeBoard");

    public final QBoard _super;

    //inherited
    public final ListPath<community.baribari.entity.star.BoardStar, community.baribari.entity.star.QBoardStar> boardStars;

    //inherited
    public final EnumPath<Category> category;

    //inherited
    public final ListPath<community.baribari.entity.comment.Comment, community.baribari.entity.comment.QComment> comments;

    //inherited
    public final StringPath content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt;

    //inherited
    public final BooleanPath deleted;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final community.baribari.entity.member.QMember member;

    //inherited
    public final StringPath title;

    //inherited
    public final NumberPath<Long> viewCount;

    public QFreeBoard(String variable) {
        this(FreeBoard.class, forVariable(variable), INITS);
    }

    public QFreeBoard(Path<? extends FreeBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFreeBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFreeBoard(PathMetadata metadata, PathInits inits) {
        this(FreeBoard.class, metadata, inits);
    }

    public QFreeBoard(Class<? extends FreeBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QBoard(type, metadata, inits);
        this.boardStars = _super.boardStars;
        this.category = _super.category;
        this.comments = _super.comments;
        this.content = _super.content;
        this.createdAt = _super.createdAt;
        this.deleted = _super.deleted;
        this.id = _super.id;
        this.member = _super.member;
        this.title = _super.title;
        this.viewCount = _super.viewCount;
    }

}

