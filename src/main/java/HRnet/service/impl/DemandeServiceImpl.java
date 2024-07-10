package HRnet.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import HRnet.dto.DemandeDTO;
import HRnet.entity.Demande;
import HRnet.repository.DemandeRepository;
import HRnet.service.DemandeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

@Service
public class DemandeServiceImpl implements DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Demande saveDemande(Demande demande) {
        return demandeRepository.save(demande);
    }

    @Override
    public Demande findById(Long id) {
        return demandeRepository.findById(id).orElse(null);
    }

    @Override
    public Demande getDemandeById(Long id) {
        return demandeRepository.findById(id).orElse(null);
    }

    @Override
    public List<DemandeDTO> getAllDemandes() {
        List<Demande> demandes = demandeRepository.findAll();
        return modelMapper.map(demandes, new TypeToken<List<DemandeDTO>>() {}.getType());
        
    }

    @Override
    public List<DemandeDTO> getDemandesByEmployeeId(Long employeeId) {
        List<Demande> demandes = demandeRepository.findByEmployeeId(employeeId);
        return modelMapper.map(demandes, new TypeToken<List<DemandeDTO>>() {}.getType());
    }

    @Override
    public DemandeDTO updateDemande(DemandeDTO demandeDTO) {
        if (demandeDTO.getId() == null) {
            throw new IllegalArgumentException("Demande ID cannot be null");
        }
    
        Optional<Demande> existingDemande = demandeRepository.findById(demandeDTO.getId());
        if (existingDemande.isPresent()) {
            Demande demandeToUpdate = modelMapper.map(demandeDTO, Demande.class);
            demandeRepository.save(demandeToUpdate);
            return demandeDTO;
        } else {
            throw new RuntimeException("Demande not found with id;" + demandeDTO.getId());
        }
    }

    @Override
    public void deleteDemande(Long id) {
        demandeRepository.deleteById(id);
    }

}
