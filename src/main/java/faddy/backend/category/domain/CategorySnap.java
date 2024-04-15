package faddy.backend.category.domain;

import faddy.backend.global.BaseEntity;
import faddy.backend.snap.domain.Snap;
import jakarta.persistence.*;

@Entity
@Table(name = "category_snap")
public class CategorySnap extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_snap_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "snap_id")
    private Snap snap;

}
