package faddy.backend.styleBoard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStyleBoard is a Querydsl query type for StyleBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStyleBoard extends EntityPathBase<StyleBoard> {

    private static final long serialVersionUID = 883162352L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStyleBoard styleBoard = new QStyleBoard("styleBoard");

    public final faddy.backend.global.QBaseEntity _super = new faddy.backend.global.QBaseEntity(this);

    public final faddy.backend.user.domain.QUser author;

    public final EnumPath<Category> category = createEnum("category", Category.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_at = _super.created_at;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated_at = _super.updated_at;

    public final NumberPath<Integer> views = createNumber("views", Integer.class);

    public QStyleBoard(String variable) {
        this(StyleBoard.class, forVariable(variable), INITS);
    }

    public QStyleBoard(Path<? extends StyleBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStyleBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStyleBoard(PathMetadata metadata, PathInits inits) {
        this(StyleBoard.class, metadata, inits);
    }

    public QStyleBoard(Class<? extends StyleBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new faddy.backend.user.domain.QUser(forProperty("author"), inits.get("author")) : null;
    }

}

