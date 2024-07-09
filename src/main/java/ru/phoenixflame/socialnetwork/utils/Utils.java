package ru.phoenixflame.socialnetwork.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
@Slf4j
public class Utils {

    private static final String  DATE_PATTERN = "yyyy-MM-dd";

    /**
     * Хэширование паролей
     *
     * @param password - пароль
     * @return хэш-строка
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return new String(digest.digest(password.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * Форматирование строки в дату
     *
     * @param date - строка
     * @return дата
     */
    public static LocalDate dateFromString(String date)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, formatter);
    }

    /**
     * Форматирование строки в дату
     *
     * @param date - дата
     * @return строка
     */
    public static String stringFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return date.format(formatter);
    }
}
