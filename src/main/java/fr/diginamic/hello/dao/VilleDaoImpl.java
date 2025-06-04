package fr.diginamic.hello.dao;

import fr.diginamic.hello.entity.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;


/**
 * The type Ville dao.
 */
public class VilleDaoImpl implements VilleDao{


    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloApp");

    /*public VilleDaoImpl(){
        this.emf = Persistence.createEntityManagerFactory("HelloApp");
    }*/
    @Override
    public void create(Ville ville) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(ville);
        et.commit();
        em.close();
    }

    @Override
    public Ville read(int id) {
        EntityManager em = emf.createEntityManager();
        Ville ville = em.find(Ville.class, id);
        em.close();
        return ville;
    }

    /**
     * Read by name ville.
     *
     * @param nom the nom
     * @return the ville
     */
    public Ville readByName(String nom){
        EntityManager em = emf.createEntityManager();
        Ville ville = em.createQuery("SELECT v FROM Ville v WHERE v.nom =:nom", Ville.class).setParameter("nom", nom).getSingleResult();
        em.close();
        return ville;
    }

    /**
     * Read all list.
     *
     * @return the list
     */
    public List<Ville> readAll(){
        EntityManager em = emf.createEntityManager();
        List<Ville> villes = em.createQuery("SELECT v FROM Ville v", Ville.class).getResultList();
        em.close();
        return villes;
    }

    @Override
    public void update(int id, Ville villeData) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Ville ville = em.find(Ville.class, id);
        if(ville != null){
            ville.setNom(villeData.getNom());
            ville.setNbHabitants(villeData.getNbHabitants());
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Ville ville = em.find(Ville.class, id);

        if(ville != null){
            em.remove(ville);
        }

        em.getTransaction().commit();
        em.close();
    }
}
