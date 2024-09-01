package community.baribari.entity.report;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardReport is a Querydsl query type for BoardReport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardReport extends EntityPathBase<BoardReport> {

    private static final long serialVersionUID = -257372946L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardReport boardReport = new QBoardReport("boardReport");

    public final QReport _super;

    public final community.baribari.entity.board.QBoard board;

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

    public QBoardReport(String variable) {
        this(BoardReport.class, forVariable(variable), INITS);
    }

    public QBoardReport(Path<? extends BoardReport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardReport(PathMetadata metadata, PathInits inits) {
        this(BoardReport.class, metadata, inits);
    }

    public QBoardReport(Class<? extends BoardReport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QReport(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new community.baribari.entity.board.QBoard(forProperty("board"), inits.get("board")) : null;
        this.content = _super.content;
        this.createdAt = _super.createdAt;
        this.id = _super.id;
        this.isResolved = _super.isResolved;
        this.member = _super.member;
    }

}

