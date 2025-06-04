package fr.diginamic.hello.repository;

import fr.diginamic.hello.entity.Ville;
import io.swagger.annotations.ApiModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Ville repository.
 */
@ApiModel(description = "The interface Ville repository.")
public interface VilleRepository extends JpaRepository<Ville, Integer> {
    /**
     * Find by nom ville.
     *
     * @param nom the nom
     * @return the ville
     */
    public Ville findByNom(String nom);

    /**
     * Find by nom starting with list.
     *
     * @param nom the nom
     * @return the list
     */
    public List<Ville> findByNomStartingWith(String nom);

    /**
     * Find by nb habitants greater than order by nb habitants desc list.
     *
     * @param min the min
     * @return the list
     */
    public List<Ville> findByNbHabitantsGreaterThanOrderByNbHabitantsDesc(int min);

    /**
     * Find by nb habitants between order by nb habitants desc list.
     *
     * @param min the min
     * @param max the max
     * @return the list
     */
    public List<Ville> findByNbHabitantsBetweenOrderByNbHabitantsDesc(int min, int max);

    /**
     * Find by departement code and nb habitants greater than order by nb habitants desc list.
     *
     * @param code the code
     * @param min  the min
     * @return the list
     */
    @EntityGraph(attributePaths = "departement")
    public List<Ville> findByDepartementCodeAndNbHabitantsGreaterThanOrderByNbHabitantsDesc(int code, int min);

    /**
     * Find by departement code and nb habitants between order by nb habitants desc list.
     *
     * @param code the code
     * @param min  the min
     * @param max  the max
     * @return the list
     */
    @EntityGraph(attributePaths = "departement")
    public List<Ville> findByDepartementCodeAndNbHabitantsBetweenOrderByNbHabitantsDesc(int code, int min, int max);

    /**
     * Find by departement code order by nb habitants desc list.
     *
     * @param code the code
     * @return the list
     */
    @EntityGraph(attributePaths = "departement")
    public List<Ville> findByDepartementCodeOrderByNbHabitantsDesc(int code);

}
