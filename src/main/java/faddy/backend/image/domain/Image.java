package faddy.backend.image.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import faddy.backend.global.BaseEntity;
import faddy.backend.snap.domain.Snap;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Long id;

    @Column(name = "image_url", nullable = false, length=1024)
    private String imageUrl;

    @Column(name = "hash_name", nullable = false  , unique = true)
    private String hashName;

    @Column(name = "image_name", nullable = false)
    private String originalName;

    @Column(name = "image_size")
    private Long size; // Assuming the size is in bytes

    @Column(name = "image_format", nullable = false)
    private String format; // e.g., "jpeg", "png", etc.

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snap_id")
    private Snap snap;


    public Image(String imageUrl, String hashName, String originalName, Long size, String format) {
        this.imageUrl = imageUrl;
        this.hashName = hashName;
        this.originalName = originalName;
        this.size = size;
        this.format = format;
    }

    public void addSnap(Snap snap) {
        this.snap = snap;
    }

}
