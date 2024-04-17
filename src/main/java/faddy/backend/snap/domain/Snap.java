package faddy.backend.snap.domain;

import faddy.backend.category.domain.Category;
import faddy.backend.category.domain.CategorySnap;
import faddy.backend.global.BaseEntity;
import faddy.backend.image.domain.Image;
import faddy.backend.hashTags.domain.HashTag;
import faddy.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "snaps")
@SQLDelete(sql = "UPDATE snaps SET deleted_at = current_timestamp WHERE id = ?")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
public class Snap extends BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snap_id", nullable = false)
    private Long snapId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "snap", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> snapImages = new ArrayList<>();

    @OneToMany(mappedBy = "snap", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HashTag> hashTags = new ArrayList<>();

    @Column(name = "view_count", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int viewCount = 0;

    @Column(name = "share_count", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int shareCount = 0;

    @Column(name = "description", length = 1500)
    private String description;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt = null;

    @OneToMany(mappedBy = "snap" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<CategorySnap> categorySnaps = new ArrayList<>();

    public void recoverSnap() {
        if (this.deletedAt != null) {
            this.deletedAt = null;
            this.viewCount = 0;
            this.shareCount = 0;
        }
    }

    public Snap(User user, String description) {
        this.user = user;
        this.description = description;
    }

    public Snap(User user, List<Image> snapImages, List<HashTag> hashTags, String description) {
        this.user = user;
        this.snapImages = snapImages;
        this.hashTags = hashTags;
        this.description = description;
    }

    public void updateImages() {
        snapImages.forEach(image -> image.linkSnap(this));
    }

    public void linkUser(User user) {
        user.getSnaps().add(this);
        this.user = user;
    }

    public void updateHashTags() {
        this.hashTags.forEach(hashTag -> hashTag.linkedSnap(this));
    }

    public void addCategory(Category category) {
        CategorySnap categorySnap = new CategorySnap(category, this);
        this.categorySnaps.add(categorySnap);
        category.getCategorySnaps().add(categorySnap);
    }
}

