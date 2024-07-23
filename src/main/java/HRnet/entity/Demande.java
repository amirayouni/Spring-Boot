package HRnet.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String status;
    @ManyToOne
    @JoinTable(name = "demande_employee",
               joinColumns = @JoinColumn(name = "demande_id"),
               inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Employee employee;
 
}
