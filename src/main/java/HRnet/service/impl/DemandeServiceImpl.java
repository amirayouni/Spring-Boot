package HRnet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import HRnet.dto.DemandeDTO;
import HRnet.entity.Demande;
import HRnet.repository.DemandeRepository;
import HRnet.service.DemandeService;
import org.modelmapper.ModelMapper;
import java.util.stream.Collectors;

@Service
public class DemandeServiceImpl implements DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DemandeDTO> getAllDemandes() {
        return demandeRepository.findAll().stream()
                .map(demande -> modelMapper.map(demande, DemandeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DemandeDTO getDemandeById(Long id) {
        Demande demande = demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande not found"));
        return modelMapper.map(demande, DemandeDTO.class);
    }

    @Override
    public DemandeDTO createDemande(DemandeDTO demandeDTO) {
        Demande demande = modelMapper.map(demandeDTO, Demande.class);
        Demande savedDemande = demandeRepository.save(demande);
        return modelMapper.map(savedDemande, DemandeDTO.class);
    }

    @Override
    public DemandeDTO updateDemande(Long id, DemandeDTO demandeDTO) {
        Demande demande = demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande not found"));
        modelMapper.map(demandeDTO, demande);
        Demande updatedDemande = demandeRepository.save(demande);
        return modelMapper.map(updatedDemande, DemandeDTO.class);
    }

    @Override
    public void deleteDemande(Long id) {
        demandeRepository.deleteById(id);
    }
}