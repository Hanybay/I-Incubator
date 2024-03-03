package com.iincubator.Repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

//import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.iincubator.Entities.Entreprise;
import com.iincubator.Entities.Message;

public interface MessageRepository extends CrudRepository<Message,Long>{
    // Optional<Message> findByDateAndEmetteurAndContenu(String date, Emetteur emetteur, String contenu);
    Optional<Message> findByDateDiffusionAndSenderOrganizationAndContent(Date dateDiffusion, Entreprise senderOrganization, String contenu);
    List<Message> findContentBySenderOrganization(Entreprise senderOrganization);
}