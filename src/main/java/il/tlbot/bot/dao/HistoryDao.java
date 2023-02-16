package il.tlbot.bot.dao;

import il.tlbot.bot.models.History;

import java.util.List;
import java.util.Optional;

public interface HistoryDao {
    Optional<History> receiveHistoryById(Long id);
    List<History> receiveHistoryByUserId(Long userId);
    void saveHistory(History history);


    List<History> getAllHistory();

}
