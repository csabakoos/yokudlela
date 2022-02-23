package hu.yokudlela.haccp.spring;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the spring main class.
 *
 * @author csabakoos
 */
public class main {
    public static void main(String[] args) {
        DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("2022-02-22T10:15:30", LocalDateTime::from);
    }

}