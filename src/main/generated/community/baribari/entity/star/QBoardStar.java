package community.baribari.entity.star;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardStar is a Querydsl query type for BoardStar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardStar extends EntityPathBase<BoardStar> {

    private static final long serialVersionUID = -659893334L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardStar boardStar = new QBoardStar("boardStar");

    public final community.baribari.entity.board.QBoard board;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final community.baribari.entity.member.QMember member;

    public QBoardStar(String variable) {
        this(BoardStar.class, forVariable(variable), INITS);
    }

    public QBoardStar(Path<? extends BoardStar> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardStar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardStar(PathMetadata metadata, PathInits inits) {
        this(BoardStar.class, metadata, inits);
    }

    public QBoardStar(Class<? extends BoardStar> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new community.baribari.entity.board.QBoard(forProperty("board"), inits.get("board")) : null;
        this.member = inits.isInitialized("member") ? new community.baribari.entity.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

