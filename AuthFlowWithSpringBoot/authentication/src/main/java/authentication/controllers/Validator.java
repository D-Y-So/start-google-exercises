package authentication.controllers;

import java.util.regex.Pattern;

public class Validator {

    private static Pattern pattern;

    public static boolean isValidName(String name) {
        if(name == null) {
            return false;
        }
        String regex = "^[A-Za-z]\\w{2,29}$";
        pattern = Pattern.compile(regex);
        return pattern.matcher(name).matches();
    }

    public static boolean isValidEmail(String email) {
        if(email == null) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,20}$";

        pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        if(password == null) {
            return false;
        }
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        pattern = Pattern.compile(regex);
        return pattern.matcher(password).matches();
    }

    public static String getPasswordConstraints(){
        return "\ncontains at least 8 characters and at most 20 characters.\n" +
                "At least one digit.\n" +
                "At least one upper case alphabet.\n" +
                "At least one lower case alphabet.\n" +
                "At least one special character which includes !@#$%&*()-+=^.\n" +
                "doesn’t contain any white space.";
    }
}
