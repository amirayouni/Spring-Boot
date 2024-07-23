package HRnet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HRnet.dto.DemandeDTO;
import HRnet.service.DemandeService;

@RestController
@RequestMapping("/api/v1/demandes")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @GetMapping
    public List<DemandeDTO> getAllDemandes() {
        return demandeService.getAllDemandes();
    }

    @GetMapping("/{id}")
    public DemandeDTO getDemandeById(@PathVariable Long id) {
        return demandeService.getDemandeById(id);
    }

    @PostMapping
    public DemandeDTO createDemande(@RequestBody DemandeDTO demandeDTO) {
        return demandeService.createDemande(demandeDTO);
    }

    @PutMapping("/{id}")
    public DemandeDTO updateDemande(@PathVariable Long id, @RequestBody DemandeDTO demandeDTO) {
        return demandeService.updateDemande(id, demandeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDemande(@PathVariable Long id) {
        demandeService.deleteDemande(id);
    }
}