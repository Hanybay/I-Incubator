package com.iincubator.Repositories;

import java.util.List;

//import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.iincubator.Entities.Entreprise;
import com.iincubator.Entities.Message;

public interface EntrepriseRepo extends CrudRepository<Entreprise,Long>{
    boolean existsByName(String name);
    Entreprise findByName(String name);
    List<Entreprise> findAll();
    // List<Message> findBySenderOrganization(Entreprise senderOrganization);
}
