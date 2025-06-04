package fr.diginamic.hello.repository;

import fr.diginamic.hello.entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {
    //public void create(Departement departement);
}
