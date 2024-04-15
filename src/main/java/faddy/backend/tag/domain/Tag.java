package faddy.backend.tag.domain;

import faddy.backend.global.BaseEntity;
import faddy.backend.snap.domain.Snap;
import faddy.backend.type.ContentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "tags")
@NoArgsConstructor(access = PROTECTED)
public class Tag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "snap_id" )
    private Snap snap;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ContentType type;

    public Tag(String name, ContentType type) {
        this.name = name;
        this.type = type;
    }

    public Tag createTagInstance(String name, ContentType contentType) {
        return new Tag(name , contentType);
    }

}
