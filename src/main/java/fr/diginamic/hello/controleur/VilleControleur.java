package fr.diginamic.hello.controleur;

import fr.diginamic.hello.Ville;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
    @Autowired
    private Validator validator;

    private List<Ville> listVille = new ArrayList<>();

    public VilleControleur(){
        this.listVille.addAll(Arrays.stream(new Ville[] {
                new Ville("Lille", 500000),
                new Ville("Avignon", 200000),
                new Ville("Paris", 700000)}).toList());
    }

    @GetMapping
    public List<Ville> returnVilles(){
       return listVille;
    }
    @PostMapping
    public ResponseEntity<String> addVille(@Valid @RequestBody Ville villeAjout, BindingResult result){
        Errors errors = validator.validateObject(villeAjout);
        if(errors.hasErrors() || result.hasErrors()) return ResponseEntity.badRequest().body("La ville saisie es incorrecte");
        for(Ville ville : listVille){
            if(ville.equals(villeAjout)) return ResponseEntity.badRequest().body("La ville existe déjà");
        }
        this.listVille.add(villeAjout);
        return ResponseEntity.ok().body("Ville insérée avec succès");
    }

    @GetMapping(path = "/{id}")
    public Ville getById(@PathVariable int id){
        for(Ville ville : listVille){
            if(ville.getId() == id) return ville;
        }
        return null;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> editVille(@Valid @PathVariable int id, @RequestBody Ville villeModif, BindingResult result){
        Errors errors = validator.validateObject(villeModif);
        if(errors.hasErrors() || result.hasErrors()) return ResponseEntity.badRequest().body("La ville saisie es incorrecte");
        for(Ville ville : listVille){
            if(ville.getId() == id){
                ville.setNom(villeModif.getNom());
                ville.setNbHabitants(villeModif.getNbHabitants());
                return ResponseEntity.ok("Ville modifiée");
            };
        }
        return ResponseEntity.badRequest().body("Ville non trouvée");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id){
        for(Ville ville : listVille) {
            if (ville.getId() == id) {
                listVille.remove(ville);
                return ResponseEntity.ok("Ville supprimée");
            }
        }
            return ResponseEntity.badRequest().body("Ville non trouvée");
    }

}
