package ru.chursinov.webmeetingbot.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.chursinov.webmeetingbot.entity.UserAnswersEntity;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAnswersModel {

    int id;
    String date;
    String username;
    long userid;
    String yesterday;
    String today;
    String problem;
    String problem_details;

    public static UserAnswersModel toModel(UserAnswersEntity entity) {
        UserAnswersModel model = new UserAnswersModel();
        model.setId(entity.getId());
        model.setDate(entity.getDate());
        model.setUsername(entity.getUsername());
        model.setUserid(entity.getUserid());
        model.setYesterday(entity.getYesterday());
        model.setToday(entity.getToday());
        model.setProblem(entity.getProblem());
        model.setProblem_details(entity.getProblem_details());

        return model;
    }

}
