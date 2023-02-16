package il.tlbot.bot.repository;

import il.tlbot.bot.models.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDialogHistoryRepository extends MongoRepository<History, Long> {
    List<History> findAllHistoryByUserId(Long userId);

}
