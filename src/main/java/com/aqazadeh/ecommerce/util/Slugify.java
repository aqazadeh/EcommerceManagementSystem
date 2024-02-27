package com.aqazadeh.ecommerce.util;

import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import org.springframework.stereotype.Component;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 18.02.2024
 * Time: 13:00
 */
@Component
public class Slugify {

    public String encode(String title, Long id) {
        return id.toString() + "-" + removeSpecialCharacters(title);
    }

    public String encode(String title) {

        return String.valueOf(removeSpecialCharacters(title));
    }


    private StringBuilder removeSpecialCharacters(String text) {
        StringBuilder result = new StringBuilder();
        text = text.trim().toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (Character.isWhitespace(currentChar)) {
                result.append("-");
            }
            if (Character.isLetterOrDigit(currentChar)) {
                result.append(changeLettersToEng(currentChar));
            }
        }
        return result;
    }

    private Character changeLettersToEng(Character character) {
        return switch (character) {
            case 'ə' -> 'e';
            case 'ı' -> 'i';
            case 'ö' -> 'o';
            case 'ü' -> 'u';
            case 'ç' -> 'c';
            case 'ş' -> 's';
            default -> character;
        };
    }


    public Long decode(String endpoint) {
        try {
            String s = endpoint.split("-")[0];
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            throw new ApplicationException(ExceptionType.INVALID_SLUG);
        }
    }
}
