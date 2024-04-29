package faddy.backend.category.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategorySnap is a Querydsl query type for CategorySnap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategorySnap extends EntityPathBase<CategorySnap> {

    private static final long serialVersionUID = -1786380884L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategorySnap categorySnap = new QCategorySnap("categorySnap");

    public final faddy.backend.global.QBaseEntity _super = new faddy.backend.global.QBaseEntity(this);

    public final QCategory category;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_at = _super.created_at;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final faddy.backend.snap.domain.QSnap snap;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated_at = _super.updated_at;

    public QCategorySnap(String variable) {
        this(CategorySnap.class, forVariable(variable), INITS);
    }

    public QCategorySnap(Path<? extends CategorySnap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCategorySnap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCategorySnap(PathMetadata metadata, PathInits inits) {
        this(CategorySnap.class, metadata, inits);
    }

    public QCategorySnap(Class<? extends CategorySnap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category"), inits.get("category")) : null;
        this.snap = inits.isInitialized("snap") ? new faddy.backend.snap.domain.QSnap(forProperty("snap"), inits.get("snap")) : null;
    }

}

