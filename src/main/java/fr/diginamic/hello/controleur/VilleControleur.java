package fr.diginamic.hello.controleur;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import fr.diginamic.hello.exception.InvalidRequestParameterException;
import fr.diginamic.hello.repository.VilleRepository;
import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.service.VilleService;
import io.swagger.annotations.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * The type Ville controleur.
 */

@Api(value = "/villes", tags = {"@GetMapping   public List<Ville> returnVilles(){      return listVille;   }   @PostMapping   public ResponseEntity<String> addVille(@Valid @RequestBody Ville villeAjout, BindingResult result){       Errors errors = validator.validateObject(villeAjout);       if(errors.hasErrors() || result.hasErrors()) return ResponseEntity.badRequest().body(\"La ville saisie es incorrecte\");       for(Ville ville : listVille){           if(ville.equals(villeAjout)) return ResponseEntity.badRequest().body(\"La ville existe déjà\");       }       this.listVille.add(villeAjout);       return ResponseEntity.ok().body(\"Ville insérée avec succès\");   }   @GetMapping(path = \" / {id}\")   public Ville getById(@PathVariable int id){       for(Ville ville : listVille){           if(ville.getId() == id) return ville;       }       return null;   }   @PutMapping(path = \" / {id}\")   public ResponseEntity<String> editVille(@Valid @PathVariable int id, @RequestBody Ville villeModif, BindingResult result){       Errors errors = validator.validateObject(villeModif);       if(errors.hasErrors() || result.hasErrors()) return ResponseEntity.badRequest().body(\"La ville saisie es incorrecte\");       for(Ville ville : listVille){           if(ville.getId() == id){               ville.setNom(villeModif.getNom());               ville.setNbHabitants(villeModif.getNbHabitants());               return ResponseEntity.ok(\"Ville modifiée\");           };       }       return ResponseEntity.badRequest().body(\"Ville non trouvée\");   }   @DeleteMapping(path = \" / {id}\")   public ResponseEntity<String> deleteVille(@PathVariable int id){       for(Ville ville : listVille) {           if (ville.getId() == id) {               listVille.remove(ville);               return ResponseEntity.ok(\"Ville supprimée\");           }       }           return ResponseEntity.badRequest().body(\"Ville non trouvée\");/"})
@RestController
@RequestMapping("/villes")
public class VilleControleur {
    @Autowired
    private Validator validator;
    /**
     * The Ville service.
     */
    VilleService villeService;
    /**
     * The Ville repository.
     */
    @Autowired
    VilleRepository villeRepository;
    //private List<Ville> listVille = new ArrayList<>();

    /**
     * Instantiates a new Ville controleur.
     */
    public VilleControleur(){
        villeService = new VilleService();
        /*this.listVille.addAll(Arrays.stream(new Ville[] {
                new Ville("Lille", 500000),
                new Ville("Avignon", 200000),
                new Ville("Paris", 700000)}).toList());*/
    }

    /**
     * Traiter erreurs response entity.
     *
     * @param irp the irp
     * @return the response entity
     */
    @ExceptionHandler({InvalidRequestParameterException.class})
    public ResponseEntity<String> traiterErreurs(InvalidRequestParameterException irp){
        return ResponseEntity.badRequest().body(irp.getMessage());
    }

    /**
     * Find villes list.
     *
     * @return the list
     */
    @ApiOperation(value = "Find villes list.", notes = "Find villes list.", httpMethod = "GET")
    @GetMapping(path = "/repo")
    public List<Ville> findVilles(){
        return villeRepository.findAll();
    }

    /**
     * Find ville ville.
     *
     * @param id the id
     * @return the ville
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "the id", required = true)
    })
    @ApiOperation(value = "Find ville ville.", notes = "Find ville ville.", httpMethod = "GET")
    @GetMapping(path = "/repo/{id}")
    public Ville findVille(@PathVariable int id){
        return villeRepository.findById(id).orElse(null);
    }

    /**
     * Find ville by nom ville.
     *
     * @param nom the nom
     * @return the ville
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "string", name = "nom", value = "the nom", required = true)
    })
    @ApiOperation(value = "Find ville by nom ville.", notes = "Find ville by nom ville.", httpMethod = "GET")
    @GetMapping(path = "/repo/nom={nom}")
    public Ville findVilleByNom(@PathVariable String nom){
        return villeRepository.findByNom(nom);
    }

    /**
     * Find by nom starting with list.
     *
     * @param nom the nom
     * @return the list
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "string", name = "nom", value = "the nom", required = true)
    })
    @ApiOperation(value = "Find by nom starting with list.", notes = "Find by nom starting with list.", httpMethod = "GET")
    @GetMapping(path = "/repo/{nom}")
    public List<Ville> findByNomStartingWith(@PathVariable String nom){
        return villeRepository.findByNomStartingWith(nom);
    }

    /**
     * Find by nb habitants greater than order by nb habitants desc list.
     *
     * @param min the min
     * @return the list
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "min", value = "the min", required = true)
    })
    @ApiOperation(value = "Find by nb habitants greater than order by nb habitants desc list.", notes = "Find by nb habitants greater than order by nb habitants desc list.", httpMethod = "GET")
    @GetMapping(path = "/repo/villeGreaterThan/{min}")
    public List<Ville> findByNbHabitantsGreaterThanOrderByNbHabitantsDesc(@PathVariable int min){
        return villeRepository.findByNbHabitantsGreaterThanOrderByNbHabitantsDesc(min);
    }
    @GetMapping(path = "/repoExportCSV/villeGreaterThan/{min}")
    public void exportToCSVVilleswithNbHabitantsGreaterThan(@PathVariable int min, HttpServletResponse response) throws IOException, DocumentException {
        response.setHeader("Content-Disposition", "attachment; filename=\"fichier.csv\"");
        List<Ville> villes = findByNbHabitantsGreaterThanOrderByNbHabitantsDesc(min);
        String separator = ";";
        for (Ville ville : villes){
            response.getWriter().append(String.valueOf(ville.getId())).append(separator)
                    .append(ville.getNom()).append(separator)
                    .append(String.valueOf(ville.getNbHabitants())).append(separator)
                    .append(String.valueOf(ville.getDepartement().getId())).append(separator)
                    .append(ville.getDepartement().getNom()).append(separator)
                    .append(String.valueOf(ville.getDepartement().getCode())).append("\n");
        }
        response.flushBuffer();
    }

    @GetMapping(path = "/repoExportPDF/villeGreaterThan/{min}")
    public void exportToPDFVilleswithNbHabitantsGreaterThan(@PathVariable int min, HttpServletResponse response) throws IOException, DocumentException {
        response.setHeader("Content-Disposition", "attachment; filename=\"fichier.pdf\"");
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        List<Ville> villes = findByNbHabitantsGreaterThanOrderByNbHabitantsDesc(min);
        document.open();
        document.addTitle("Fiche");
        document.newPage();
        BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
        for (Ville ville : villes){
            document.add(new Phrase(ville.toString(), new Font(baseFont)));
        }
        document.close();
        response.flushBuffer();
    }
    /**
     * Find by nb habitants between order by nb habitants desc list.
     *
     * @param min the min
     * @param max the max
     * @return the list
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "min", value = "the min", required = true),
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "max", value = "the max", required = true)
    })
    @ApiOperation(value = "Find by nb habitants between order by nb habitants desc list.", notes = "Find by nb habitants between order by nb habitants desc list.", httpMethod = "GET")
    @GetMapping(path = "/repo/between/{min}/and/{max}")
    public List<Ville> findByNbHabitantsBetweenOrderByNbHabitantsDesc(@PathVariable int min,@PathVariable int max){
        return villeRepository.findByNbHabitantsBetweenOrderByNbHabitantsDesc(min, max);
    }

    /**
     * Find by departement code and nb habitants greater than order by nb habitants desc list.
     *
     * @param departementCode the departement code
     * @param min             the min
     * @return the list
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "departementCode", value = "the departement code", required = true),
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "min", value = "the min", required = true)
    })
    @ApiOperation(value = "Find by departement code and nb habitants greater than order by nb habitants desc list.", notes = "Find by departement code and nb habitants greater than order by nb habitants desc list.", httpMethod = "GET")
    @GetMapping(path = "/repo/{departementCode}/greaterThan/{min}")
    public List<Ville> findByDepartementCodeAndNbHabitantsGreaterThanOrderByNbHabitantsDesc(@PathVariable int departementCode,@PathVariable int min){
        return villeRepository.findByDepartementCodeAndNbHabitantsGreaterThanOrderByNbHabitantsDesc(departementCode, min);
    }

    /**
     * Find by departement code and nb habitants between order by nb habitants desc list.
     *
     * @param departementCode the departement code
     * @param min             the min
     * @param max             the max
     * @return the list
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "departementCode", value = "the departement code", required = true),
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "min", value = "the min", required = true),
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "max", value = "the max", required = true)
    })
    @ApiOperation(value = "Find by departement code and nb habitants between order by nb habitants desc list.", notes = "Find by departement code and nb habitants between order by nb habitants desc list.", httpMethod = "GET")
    @GetMapping(path = "/repo/{departementCode}/between/{min}/and/{max}")
    public List<Ville> findByDepartementCodeAndNbHabitantsBetweenOrderByNbHabitantsDesc(@PathVariable int departementCode, @PathVariable int min,@PathVariable int max){
        return villeRepository.findByDepartementCodeAndNbHabitantsBetweenOrderByNbHabitantsDesc(departementCode, min, max);
    }

    /**
     * Find top nb by departement code order by nb habitants desc list.
     *
     * @param departementCode the departement code
     * @param nb              the nb
     * @return the list
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "departementCode", value = "the departement code", required = true),
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "nb", value = "the nb", required = true)
    })
    @ApiOperation(value = "Find top nb by departement code order by nb habitants desc list.", notes = "Find top nb by departement code order by nb habitants desc list.", httpMethod = "GET")
    @GetMapping(path = "/repo/{departementCode}/top/{nb}")
    public List<Ville> findTopNbByDepartementCodeOrderByNbHabitantsDesc(@PathVariable int departementCode, @PathVariable int nb){
        List<Ville>  listResult = villeRepository.findByDepartementCodeOrderByNbHabitantsDesc(departementCode);
        if (nb>listResult.size()) nb = listResult.size();
        return listResult.subList(0, nb);
    }

    /**
     * Return villes list.
     *
     * @return the list
     */
    @ApiOperation(value = "Return villes list.", notes = "Return villes list.", httpMethod = "GET")
    @GetMapping
    public List<Ville> returnVilles(){
        return villeService.extractVilles();
    }

    /**
     * Get by id ville.
     *
     * @param id the id
     * @return the ville
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "the id", required = true)
    })
    @ApiOperation(value = "Get by id ville.", notes = "Get by id ville.", httpMethod = "GET")
    @GetMapping(path = "/{id}")
    public Ville getById(@PathVariable int id){
        return villeService.extractVille(id);
    }

    /**
     * Get by id ville.
     *
     * @param nom the nom
     * @return the ville
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "string", name = "nom", value = "the nom", required = true)
    })
    @ApiOperation(value = "Get by id ville.", notes = "Get by id ville.", httpMethod = "GET")
    @GetMapping(path = "/nom={nom}")
    public Ville getById(@PathVariable String nom){
        return villeService.extractVille(nom);
    }

    /**
     * Add ville response entity.
     *
     * @param villeAjout the ville ajout
     * @param result     the result
     * @return the response entity
     * @throws InvalidRequestParameterException the invalid request parameter exception
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "Ville", name = "villeAjout", value = "the ville ajout", required = true)
    })
    @ApiOperation(value = "Add ville response entity.", notes = "Add ville response entity.", httpMethod = "POST")
    @PostMapping
    public ResponseEntity<String> addVille(@Valid @RequestBody Ville villeAjout, BindingResult result) throws InvalidRequestParameterException {
        if(result.hasErrors()) return ResponseEntity.badRequest().body("La ville saisie est incorrecte");
        List<Ville> listVille = villeService.insertVille(villeAjout);
        for(Ville ville : listVille){
            if(ville.equals(villeAjout)) return ResponseEntity.ok().body("Ville insérée avec succès");
        }
        return ResponseEntity.badRequest().body("La ville n'a pas été ajoutée");
    }

    /**
     * Edit ville response entity.
     *
     * @param id         the id
     * @param villeModif the ville modif
     * @param result     the result
     * @return the response entity
     * @throws InvalidRequestParameterException the invalid request parameter exception
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "the id", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "Ville", name = "villeModif", value = "the ville modif", required = true)
    })
    @ApiOperation(value = "Edit ville response entity.", notes = "Edit ville response entity.", httpMethod = "PUT")
    @PutMapping(path = "/{id}")
    public ResponseEntity<String> editVille(@Valid @PathVariable int id, @RequestBody Ville villeModif, BindingResult result) throws InvalidRequestParameterException {
        if(result.hasErrors()) return ResponseEntity.badRequest().body("La ville saisie es incorrecte");
        List<Ville> listVille = villeService.modifierVille(id, villeModif);
        for(Ville ville : listVille){
            if(ville.getId() == id && ville.equals(villeModif)){
                return ResponseEntity.ok("Ville modifiée");
            };
        }
        return ResponseEntity.badRequest().body("Ville non trouvée");
    }

    /**
     * Delete ville response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "the id", required = true)
    })
    @ApiOperation(value = "Delete ville response entity.", notes = "Delete ville response entity.", httpMethod = "DELETE")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id){
        List<Ville> listVille = villeService.supprimerVille(id);
        for(Ville ville : listVille) {
            if (ville.getId() == id) {
                return ResponseEntity.badRequest().body("Ville non supprimée");
            }
        }
        return ResponseEntity.ok().body("Ville supprimée");
    }


    /*@GetMapping
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
    }*/

}
