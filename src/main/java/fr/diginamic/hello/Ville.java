package fr.diginamic.hello;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Ville {

    private static int idIncrement = 0;
    @Min(0)
    private int id;
    @Size(min = 2)
    private String nom;
    @Min(1)
    private int nbHabitants;

    public Ville(String nom, int nbHabitants) {
        this.nom = nom;
        this.nbHabitants = nbHabitants;
        this.id = idIncrement++;
    }
    public boolean equals(Ville ville){
        return this.nom.equals(ville.getNom()) && this.nbHabitants == ville.getNbHabitants();
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }
}
