package ru.chursinov.webmeetingbot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chursinov.webmeetingbot.entity.UserAnswersEntity;

public interface UserAnswersRepo extends CrudRepository<UserAnswersEntity, String> {
    UserAnswersEntity findByUseridAndDate(long userid, String date);
}
