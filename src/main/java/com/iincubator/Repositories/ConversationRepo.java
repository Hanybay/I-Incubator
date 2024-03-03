package com.iincubator.Repositories;

//import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.iincubator.Entities.Conversation;

public interface ConversationRepo extends CrudRepository<Conversation,Long>{
    
}
