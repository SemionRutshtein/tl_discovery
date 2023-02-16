package il.tlbot.bot.service;

import il.tlbot.bot.dao.HistoryDao;
import il.tlbot.bot.models.Dialog;
import il.tlbot.bot.models.History;
import il.tlbot.bot.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static il.tlbot.bot.config.IdGenerator.generateId;

@Component
@RequiredArgsConstructor
public class MyTelegramBot extends TelegramLongPollingBot {

    private final HistoryDao historyDao;

    @Value("${telegram.bot.token}")
    private String token;

    @Value("${telegram.bot.username}")
    private String username;

    @Value("${openai.api.key}")
    private String openAIKey;


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();

            // Save the response to MongoDB
            History history = new History(
                    "1l",
                    update.getUpdateId(),
                    new Dialog(messageText, ""),
                    new User()
            );

            historyDao.saveHistory(history);

            // Send the response back to the user
            SendMessage message = new SendMessage();
            message.setText("");
            message.setChatId(chatId);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }


}
