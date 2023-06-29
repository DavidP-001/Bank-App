package com.example.bankapp.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCheck {

    //helper method to check if the user entered a valid input for the given prompt AND the input is not empty
    //has 4 types of checks:
    //0 -> only do a isEmpty check
    //1 -> Letters only check (For names)
    //2 -> Numbers only check (For IDs)
    //3 -> Currency input check (example: 44.44 OR 44,44)


    public static boolean ValidString (String input, int typeOfCheck)
    {

        Pattern letter = Pattern.compile("[a-zA-z]+");
        Pattern digit = Pattern.compile("[0-9]+");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]+");
        Pattern currencyInput = Pattern.compile("\\d+([.,])?\\d*");


        Matcher hasLetter = letter.matcher(input);
        Matcher hasDigit = digit.matcher(input);
        Matcher hasSpecial = special.matcher(input);
        Matcher isCurrency = currencyInput.matcher(input);

        if (input.isEmpty())
        {
            return false;
        }

        switch (typeOfCheck) {
            case 0:
                return input.isEmpty();
            case 1:
                return hasLetter.matches();
            case 2:
                return hasDigit.matches();
            case 3:
                return isCurrency.matches();
            default:
                return false;
        }

    }
}
