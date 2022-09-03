package ru.chursinov.webmeetingbot.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswersInfo {
    String date;
    String username;
    long userid;
    String yesterday;
    String today;
    String problem;
    String problem_details;
}
