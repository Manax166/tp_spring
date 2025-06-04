package fr.diginamic.hello.dao;

import fr.diginamic.hello.entity.Ville;

/**
 * The interface Ville dao.
 */
public interface VilleDao {
    /**
     * Create.
     *
     * @param ville the ville
     */
    void create(Ville ville);

    /**
     * Read ville.
     *
     * @param id the id
     * @return the ville
     */
    Ville read(int id);

    /**
     * Update.
     *
     * @param id    the id
     * @param ville the ville
     */
    void update(int id, Ville ville);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(int id);

}
