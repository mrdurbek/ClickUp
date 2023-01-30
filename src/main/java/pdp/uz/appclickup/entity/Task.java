package pdp.uz.appclickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pdp.uz.appclickup.entity.template.AbstractIntegerEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task extends AbstractIntegerEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task parentTask;

    private String startedDate;

    @CreationTimestamp
    private Timestamp startedTimeHas;

    private String dueDate;

    @CreationTimestamp
    private Timestamp dueTimeHas;

    private Long estimateTime;

    private LocalDate activeDate;


}
