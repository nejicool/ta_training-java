package org.epam.swaglabs;

import org.openqa.selenium.By;

import java.util.Random;

public class Util {
    public static String generateRandomStringWithSize(int size){
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String DIGITS = "0123456789";
        StringBuilder generatedString = new StringBuilder();
        Random RNG = new Random();
        for (int i = 0; i < size; i++) {
            String selector = RNG.nextInt(2) < 1 ? LETTERS : DIGITS;
            char selected = selector.charAt(RNG.nextInt(selector.length()));
            generatedString.append(selected);
        }
        return generatedString.toString();
    }
}
