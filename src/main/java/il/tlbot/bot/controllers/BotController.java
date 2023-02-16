package il.tlbot.bot.controllers;

import il.tlbot.bot.dao.HistoryDao;
import il.tlbot.bot.models.Dialog;
import il.tlbot.bot.models.History;
import il.tlbot.bot.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static il.tlbot.bot.config.IdGenerator.generateId;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BotController {

    private final HistoryDao historyDao;


    @GetMapping("/check")
    public ResponseEntity<List<History>> getData() {
        List<History> data = historyDao.getAllHistory();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/checkSave")
    public void saveData() {
        try{
            historyDao.saveHistory(createHistory());
        } catch (Exception e) {
            log.info("Something with creating History is going wrong!");
        }
    }

    private History createHistory() {
        return History.builder()
                .history(Dialog.builder()
                        .question("My first question")
                        .answer("My first answer")
                        .build())
                .id(generateId())
                .user(User.builder()
                        .id(generateId())
                        .phoneNumber("123987123")
                        .userName("FUS")
                        .about("About").build())
                .chatId(1)
                .build();
    }
}

