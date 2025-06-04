package fr.diginamic.hello.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;

/**
 * The type Ville.
 */
@Entity
public class Ville {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Size(min = 2)
    private String nom;
    @Min(1)
    @Column(name = "nb_habitants")
    private int nbHabitants;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_dept")
    private Departement departement;

    /**
     * Instantiates a new Ville.
     *
     * @param nom         the nom
     * @param nbHabitants the nb habitants
     * @param departement the departement
     */
    public Ville(String nom, int nbHabitants, Departement departement) {
        this.nom = nom;
        this.nbHabitants = nbHabitants;
        this.departement = departement;
    }

    /**
     * Instantiates a new Ville.
     *
     * @param nom         the nom
     * @param nbHabitants the nb habitants
     */
    public Ville(String nom, int nbHabitants) {
        this.nom = nom;
        this.nbHabitants = nbHabitants;
        this.departement = new Departement("non renseigné", 0);
    }

    /**
     * Instantiates a new Ville.
     */
    public Ville() {

    }

    /**
     * Equals boolean.
     *
     * @param ville the ville
     * @return the boolean
     */
    public boolean equals(Ville ville){
        return this.nom.equals(ville.getNom()) && this.nbHabitants == ville.getNbHabitants();
    }

    /**
     * Gets departement.
     *
     * @return the departement
     */
    public Departement getDepartement() {
        return departement;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets nom.
     *
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom.
     *
     * @param nom the nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Gets nb habitants.
     *
     * @return the nb habitants
     */
    public int getNbHabitants() {
        return nbHabitants;
    }

    /**
     * Sets nb habitants.
     *
     * @param nbHabitants the nb habitants
     */
    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    @Override
    public String toString() {
        return "Ville{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nbHabitants=" + nbHabitants +
                ", departement=" + departement.toString() +
                '}';
    }
}
