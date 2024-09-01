package community.baribari.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBariRecruit is a Querydsl query type for BariRecruit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBariRecruit extends EntityPathBase<BariRecruit> {

    private static final long serialVersionUID = 1321234354L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBariRecruit bariRecruit = new QBariRecruit("bariRecruit");

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

    public final EnumPath<BariRegion> region = createEnum("region", BariRegion.class);

    public final EnumPath<BariStatus> status = createEnum("status", BariStatus.class);

    //inherited
    public final StringPath title;

    //inherited
    public final NumberPath<Long> viewCount;

    public QBariRecruit(String variable) {
        this(BariRecruit.class, forVariable(variable), INITS);
    }

    public QBariRecruit(Path<? extends BariRecruit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBariRecruit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBariRecruit(PathMetadata metadata, PathInits inits) {
        this(BariRecruit.class, metadata, inits);
    }

    public QBariRecruit(Class<? extends BariRecruit> type, PathMetadata metadata, PathInits inits) {
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

