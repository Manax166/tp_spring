package fr.diginamic.hello.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The type Departement.
 */
@Entity
public class Departement {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String nom;
    private int code;

    /**
     * Instantiates a new Departement.
     */
    public Departement(){

    }
    private Departement(int id, String nom, int code) {
        this.id = id;
        this.nom = nom;
        this.code = code;
    }

    /**
     * Instantiates a new Departement.
     *
     * @param nom  the nom
     * @param code the code
     */
    public Departement(String nom, int code) {
        this.nom = nom;
        this.code = code;
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
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", code=" + code +
                '}';
    }
}
