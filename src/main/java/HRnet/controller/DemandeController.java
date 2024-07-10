package HRnet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HRnet.dto.DemandeDTO;
import HRnet.entity.Demande;
import HRnet.service.DemandeService;

@RestController
@RequestMapping("/api/v1/demandes")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @PostMapping
    public ResponseEntity<Demande> createDemande(@RequestBody Demande demande){
        Demande createdDemande = demandeService.saveDemande(demande);
        return new ResponseEntity<>(createdDemande,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Demande> getDemandeById(@PathVariable Long id){    
        Demande demande = demandeService.getDemandeById(id);
        return demande != null ? ResponseEntity.ok(demande) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<DemandeDTO>> getAllDemandes(){
        List<DemandeDTO> demandes = demandeService.getAllDemandes();
        return new ResponseEntity<>(demandes, HttpStatus.OK);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<DemandeDTO>> getDemandesByEmployeeId(@PathVariable Long employeeId){
        List<DemandeDTO> demandes = demandeService.getDemandesByEmployeeId(employeeId);
        return new ResponseEntity<>(demandes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandeDTO> updateDemande(@RequestBody DemandeDTO demandeDTO){
        DemandeDTO updatedDemande = demandeService.updateDemande(demandeDTO);
        return ResponseEntity.ok(updatedDemande);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemande(@PathVariable Long id){
        demandeService.deleteDemande(id);
        return ResponseEntity.noContent().build();
    }


}
