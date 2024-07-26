package pl.mikolajp.forum.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "threads")
@Data
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator")
    private User creator;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("creationDate ASC")
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}
