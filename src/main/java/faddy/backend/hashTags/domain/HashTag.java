package faddy.backend.hashTags.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import faddy.backend.global.BaseEntity;
import faddy.backend.snap.domain.Snap;
import faddy.backend.type.ContentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "snap_id" )
    private Snap snap;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ContentType type;

    @Column(name = "order_num", nullable = false)
    @Min(0) // 최소값 0
    @Max(4) // 최대값 5
    private int order;


    public HashTag(String name, int order , ContentType type) {
        this.name = name;
        this.type = type;
        this.order = order;
    }

    public HashTag createTagInstance(String name, ContentType contentType) {
        return new HashTag(name , order , contentType);
    }

    public void linkedSnap(Snap snap) {
        this.snap = snap;
        snap.getHashTags().add(this);
    }

    @Override
    public String toString() {
        return "HashTag{" +
                ", name='" + name + '\'' +
                ", order=" + order +
                '}';
    }

    public void addSnap(Snap snap) {
        this.snap = snap;
    }
}
