package HRnet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import HRnet.dto.DemandeDTO;
import HRnet.entity.Demande;
import jakarta.transaction.Transactional;

@Service
@Transactional
public interface DemandeService {
    Demande saveDemande(Demande demande);
    Demande getDemandeById(Long id);
    List<DemandeDTO> getAllDemandes();
    List<DemandeDTO> getDemandesByEmployeeId(Long employeeId);
    DemandeDTO updateDemande(DemandeDTO demandeDTO);
    void deleteDemande(Long id);
    Demande findById(Long id);
}
