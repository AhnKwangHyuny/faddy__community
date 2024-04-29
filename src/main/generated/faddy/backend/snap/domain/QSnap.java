package faddy.backend.snap.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSnap is a Querydsl query type for Snap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSnap extends EntityPathBase<Snap> {

    private static final long serialVersionUID = 787563866L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSnap snap = new QSnap("snap");

    public final faddy.backend.global.QBaseEntity _super = new faddy.backend.global.QBaseEntity(this);

    public final ListPath<faddy.backend.category.domain.CategorySnap, faddy.backend.category.domain.QCategorySnap> categorySnaps = this.<faddy.backend.category.domain.CategorySnap, faddy.backend.category.domain.QCategorySnap>createList("categorySnaps", faddy.backend.category.domain.CategorySnap.class, faddy.backend.category.domain.QCategorySnap.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_at = _super.created_at;

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final ListPath<faddy.backend.hashTags.domain.HashTag, faddy.backend.hashTags.domain.QHashTag> hashTags = this.<faddy.backend.hashTags.domain.HashTag, faddy.backend.hashTags.domain.QHashTag>createList("hashTags", faddy.backend.hashTags.domain.HashTag.class, faddy.backend.hashTags.domain.QHashTag.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> shareCount = createNumber("shareCount", Integer.class);

    public final ListPath<faddy.backend.image.domain.Image, faddy.backend.image.domain.QImage> snapImages = this.<faddy.backend.image.domain.Image, faddy.backend.image.domain.QImage>createList("snapImages", faddy.backend.image.domain.Image.class, faddy.backend.image.domain.QImage.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated_at = _super.updated_at;

    public final faddy.backend.user.domain.QUser user;

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QSnap(String variable) {
        this(Snap.class, forVariable(variable), INITS);
    }

    public QSnap(Path<? extends Snap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSnap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSnap(PathMetadata metadata, PathInits inits) {
        this(Snap.class, metadata, inits);
    }

    public QSnap(Class<? extends Snap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new faddy.backend.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

