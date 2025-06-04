package fr.diginamic.hello.service;

import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.repository.DepartementRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartementService {
    DepartementRepository departementRepository;

    public void create(Departement departement){
        //departementRepository.create(departement);
    }
    public void edit(int id, Departement newDepartement){

    }

}
