package community.baribari.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBariReview is a Querydsl query type for BariReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBariReview extends EntityPathBase<BariReview> {

    private static final long serialVersionUID = -1342295964L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBariReview bariReview = new QBariReview("bariReview");

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

    public QBariReview(String variable) {
        this(BariReview.class, forVariable(variable), INITS);
    }

    public QBariReview(Path<? extends BariReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBariReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBariReview(PathMetadata metadata, PathInits inits) {
        this(BariReview.class, metadata, inits);
    }

    public QBariReview(Class<? extends BariReview> type, PathMetadata metadata, PathInits inits) {
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

