package HRnet.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import HRnet.entity.Demande;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long>{

}
