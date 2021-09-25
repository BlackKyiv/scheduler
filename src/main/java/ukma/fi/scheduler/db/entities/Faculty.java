package ukma.fi.scheduler.db.entities;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Faculty {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
}