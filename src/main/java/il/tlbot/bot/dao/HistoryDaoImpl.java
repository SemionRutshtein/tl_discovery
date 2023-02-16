package il.tlbot.bot.dao;

import il.tlbot.bot.models.History;
import il.tlbot.bot.repository.UserDialogHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class HistoryDaoImpl implements HistoryDao{
    private final UserDialogHistoryRepository userDialogHistoryRepository;


    @Override
    public Optional<History> receiveHistoryById(Long id) {
        return userDialogHistoryRepository.findById(id);
    }

    @Override
    public List<History> receiveHistoryByUserId(Long userId) {
        return userDialogHistoryRepository.findAllHistoryByUserId(userId);
    }

    @Override
    public void saveHistory(History history) {

        try{
            userDialogHistoryRepository.save(history);
        } catch (Exception e) {
            log.error("Error by writing new history entity to db ...  {}", e.getMessage());
        }
    }

    @Override
    public List<History> getAllHistory() {
        return userDialogHistoryRepository.findAll();
    }
}
