package fr.diginamic.hello.service;

import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.dao.VilleDaoImpl;
import fr.diginamic.hello.exception.InvalidRequestParameterException;
import fr.diginamic.hello.repository.VilleRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Ville service.
 */
@ApiModel(description = "The type Ville service.")
@Service
public class VilleService {
    /**
     * The Vdao.
     */
    @ApiModelProperty("The Vdao.")
    VilleDaoImpl vdao = new VilleDaoImpl();
    /**
     * The Ville repository.
     */
    @ApiModelProperty("The Ville repository.")
    VilleRepository villeRepository;

    /**
     * Extract villes list.
     *
     * @return the list
     */
    public  List<Ville> extractVilles() {
        return vdao.readAll();
    }

    /**
     * Extract ville ville.
     *
     * @param idVille the id ville
     * @return the ville
     */
    public Ville extractVille(int idVille){
        return vdao.read(idVille);
    }

    /**
     * Extract ville ville.
     *
     * @param nom the nom
     * @return the ville
     */
    public Ville extractVille(String nom){
        return vdao.readByName(nom);
    }

    /**
     * Insert ville list.
     *
     * @param ville the ville
     * @return the list
     * @throws InvalidRequestParameterException the invalid request parameter exception
     */
    public List<Ville> insertVille(Ville ville) throws InvalidRequestParameterException {
        if(ville.getNbHabitants()<10) throw new InvalidRequestParameterException("Le nombre d'habitants est inférieur à 10");
        if(ville.getNom().length()<2) throw new InvalidRequestParameterException("Le nom de la ville est trop court");
        for(Ville v : villeRepository.findByNomStartingWith(ville.getNom())){
            if(v.getNom().equals(ville.getNom()) && v.getDepartement().getCode() == ville.getDepartement().getCode()) throw new InvalidRequestParameterException("Le nom de ville doit être unique par département");
        }
        vdao.create(ville);
        return extractVilles();
    }

    /**
     * Modifier ville list.
     *
     * @param idVille       the id ville
     * @param villeModifiee the ville modifiee
     * @return the list
     * @throws InvalidRequestParameterException the invalid request parameter exception
     */
    public List<Ville> modifierVille(int idVille, Ville villeModifiee) throws InvalidRequestParameterException {
        if(villeModifiee.getNbHabitants()<10) throw new InvalidRequestParameterException("Le nombre d'habitants est inférieur à 10");
        if(villeModifiee.getNom().length()<2) throw new InvalidRequestParameterException("Le nom de la ville est trop court");
        for(Ville v : villeRepository.findByNomStartingWith(villeModifiee.getNom())){
            if(v.getNom().equals(villeModifiee.getNom()) && v.getDepartement().getCode() == villeModifiee.getDepartement().getCode()) throw new InvalidRequestParameterException("Le nom de ville doit être unique par département");
        }
        vdao.update(idVille, villeModifiee);
        return extractVilles();
    }

    /**
     * Supprimer ville list.
     *
     * @param idVille the id ville
     * @return the list
     */
    public List<Ville> supprimerVille(int idVille){
        vdao.delete(idVille);
        return extractVilles();
    }
}
