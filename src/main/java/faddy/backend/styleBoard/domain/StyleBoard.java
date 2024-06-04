package faddy.backend.styleBoard.domain;

import faddy.backend.global.BaseEntity;
import faddy.backend.user.domain.User;
import jakarta.persistence.*;

@Entity
public class StyleBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "style_board_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(length = 60, nullable = false)
    private String title;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT" , nullable = false)
    private String content;

    @Column(name = "views")
    private Long views;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;




}
