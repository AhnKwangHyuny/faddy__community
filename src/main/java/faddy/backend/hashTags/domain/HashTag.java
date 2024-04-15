package faddy.backend.hashTags.domain;

import faddy.backend.global.BaseEntity;
import faddy.backend.snap.domain.Snap;
import faddy.backend.type.ContentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "hashTags")
@NoArgsConstructor(access = PROTECTED)
public class HashTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashTag_id")
    private Long hashTagId;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "snap_id" )
    private Snap snap;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ContentType type;

    public HashTag(String name, ContentType type) {
        this.name = name;
        this.type = type;
    }

    public HashTag createTagInstance(String name, ContentType contentType) {
        return new HashTag(name , contentType);
    }

}
