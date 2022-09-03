package ru.chursinov.webmeetingbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chursinov.webmeetingbot.entity.UserAnswersEntity;
import ru.chursinov.webmeetingbot.model.UserAnswersModel;
import ru.chursinov.webmeetingbot.repository.UserAnswersRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAnswersService {

    private UserAnswersRepo userAnswersRepo;

    @Autowired
    public UserAnswersService(UserAnswersRepo userAnswersRepo) {
        this.userAnswersRepo = userAnswersRepo;
    }

    public List<UserAnswersModel> getAllAnswers() {
        List<UserAnswersModel> allAnswers = new ArrayList<>();
        Iterable<UserAnswersEntity> answers = userAnswersRepo.findAll();
        for (UserAnswersEntity entity : answers) {
            allAnswers.add(UserAnswersModel.toModel(entity));
        }
        return allAnswers;
    }

}
