package faddy.backend.styleBoardComment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStyleBoardComment is a Querydsl query type for StyleBoardComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStyleBoardComment extends EntityPathBase<StyleBoardComment> {

    private static final long serialVersionUID = -8333106L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStyleBoardComment styleBoardComment = new QStyleBoardComment("styleBoardComment");

    public final faddy.backend.global.QBaseEntity _super = new faddy.backend.global.QBaseEntity(this);

    public final faddy.backend.user.domain.QUser author;

    public final ListPath<StyleBoardComment, QStyleBoardComment> children = this.<StyleBoardComment, QStyleBoardComment>createList("children", StyleBoardComment.class, QStyleBoardComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_at = _super.created_at;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final QStyleBoardComment parent;

    public final faddy.backend.styleBoard.domain.QStyleBoard styleBoard;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated_at = _super.updated_at;

    public QStyleBoardComment(String variable) {
        this(StyleBoardComment.class, forVariable(variable), INITS);
    }

    public QStyleBoardComment(Path<? extends StyleBoardComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStyleBoardComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStyleBoardComment(PathMetadata metadata, PathInits inits) {
        this(StyleBoardComment.class, metadata, inits);
    }

    public QStyleBoardComment(Class<? extends StyleBoardComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new faddy.backend.user.domain.QUser(forProperty("author"), inits.get("author")) : null;
        this.parent = inits.isInitialized("parent") ? new QStyleBoardComment(forProperty("parent"), inits.get("parent")) : null;
        this.styleBoard = inits.isInitialized("styleBoard") ? new faddy.backend.styleBoard.domain.QStyleBoard(forProperty("styleBoard"), inits.get("styleBoard")) : null;
    }

}

