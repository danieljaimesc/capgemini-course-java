package com.example.dni_validation;

import java.util.regex.Pattern;

public class DniValidator {
    private static final Character[] letterAssigned = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B',
            'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

    static boolean validate(final String dni) {
        char letterUpperCase;
        String dniNumber = "";

        if (dni == null || dni.trim().equals("")) return false;
        if (dni.length() != 9) return false;
        letterUpperCase = dni.charAt(dni.length() - 1);
        dniNumber = dni.substring(0, dni.length() - 1);
        if (dniNumber.equals("00000000")) return false;
        if (!Character.isLetter(letterUpperCase)) return false;
        if (!Character.isUpperCase(letterUpperCase)) return false;
        if (!Pattern.matches("[0-9]+[\\.]?[0-9]*", dniNumber)) return false;
        int myDNI = Integer.parseInt(dniNumber);
        int rest = myDNI % 23;
        return letterAssigned[rest] == letterUpperCase;
    }
}
