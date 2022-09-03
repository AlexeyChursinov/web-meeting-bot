package ru.chursinov.webmeetingbot.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "user_answers")
public class UserAnswersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String date;
    String username;
    long userid;
    String yesterday;
    String today;
    String problem;
    String problem_details;

}
