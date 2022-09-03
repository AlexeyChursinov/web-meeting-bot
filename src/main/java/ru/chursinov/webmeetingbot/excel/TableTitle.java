package ru.chursinov.webmeetingbot.excel;

public enum TableTitle {
    DATE("Дата"),
    USER_NAME("Имя пользователя"),
    YESTERDAY("Сделано вчера"),
    TODAY("Планы на сегодня"),
    IS_PROBLEM("Есть проблемы?"),
    PROBLEM_DETAILS("Описание проблем");

    private final String title;

    TableTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
