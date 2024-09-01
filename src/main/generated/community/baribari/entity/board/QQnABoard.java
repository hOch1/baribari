package community.baribari.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQnABoard is a Querydsl query type for QnABoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQnABoard extends EntityPathBase<QnABoard> {

    private static final long serialVersionUID = 1348739000L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQnABoard qnABoard = new QQnABoard("qnABoard");

    public final QBoard _super;

    public final ListPath<Answer, QAnswer> answers = this.<Answer, QAnswer>createList("answers", Answer.class, QAnswer.class, PathInits.DIRECT2);

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

    public QQnABoard(String variable) {
        this(QnABoard.class, forVariable(variable), INITS);
    }

    public QQnABoard(Path<? extends QnABoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQnABoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQnABoard(PathMetadata metadata, PathInits inits) {
        this(QnABoard.class, metadata, inits);
    }

    public QQnABoard(Class<? extends QnABoard> type, PathMetadata metadata, PathInits inits) {
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

