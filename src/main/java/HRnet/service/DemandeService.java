package HRnet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import HRnet.dto.DemandeDTO;
import jakarta.transaction.Transactional;

@Service
@Transactional
public interface DemandeService {
    List<DemandeDTO> getAllDemandes();
    DemandeDTO getDemandeById(Long id);
    DemandeDTO createDemande(DemandeDTO demandeDTO);
    DemandeDTO updateDemande(Long id, DemandeDTO demandeDTO);
    void deleteDemande(Long id);
}