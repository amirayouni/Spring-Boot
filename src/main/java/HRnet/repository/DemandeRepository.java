package HRnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import HRnet.entity.Demande;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long>{
    List<Demande> findByEmployeeId(Long employeeId);

}
