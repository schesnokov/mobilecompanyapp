package com.mobilecompany.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Validator patterns.
 */
public class ValidatorPatterns {

    /**
     * Email pattern boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean emailPattern(String email){
        Pattern pattern = Pattern.compile("^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$", Pattern.CASE_INSENSITIVE);
        return match(pattern, email);
    }

    /**
     * Date pattern boolean.
     *
     * @param date the date
     * @return the boolean
     */
    public static boolean datePattern(String date) {
        Pattern pattern = Pattern.compile("^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$", Pattern.CASE_INSENSITIVE);
        return match(pattern, date);
    }

    /**
     * Parole pattern boolean.
     *
     * @param parole the parole
     * @return the boolean
     */
    public static boolean parolePattern(String parole){
        Pattern pattern = Pattern.compile("([a-z0-9_-]{1,32})", Pattern.CASE_INSENSITIVE);
        return match(pattern, parole);
    }

    /**
     * Passport pattern boolean.
     *
     * @param passport the passport
     * @return the boolean
     */
    public static boolean passportPattern(String passport) {
        Pattern pattern = Pattern.compile("([0-9]{10})", Pattern.CASE_INSENSITIVE);
        return match(pattern, passport);
    }

    /**
     * Phonenumber pattern boolean.
     *
     * @param phone the phone
     * @return the boolean
     */
    public static boolean phonenumberPattern(String phone){
        Pattern pattern = Pattern.compile("([0-9]{8,11})");
        return match(pattern, phone);
    }

    /**
     * Tariff or option name pattern boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean tariffOrOptionNamePattern(String name) {
        Pattern pattern = Pattern.compile("([a-zA-Z0-9\\s]{15})");
        return match(pattern, name);
    }

    /**
     * Price pattern boolean.
     *
     * @param price the price
     * @return the boolean
     */
    public static boolean pricePattern(String price) {
        Pattern pattern = Pattern.compile("([0-9]{8})");
        return match(pattern, price);
    }

    private static boolean match(Pattern pattern, String value){
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
