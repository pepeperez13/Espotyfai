package business;

public class UserManager {

    //TODO
    public boolean checkUsernameExistance (String name) {
        return false;
    }

    //TODO
    public boolean checkEmailExistance (String email) {
        return false;
    }

    public boolean checkEmailFormat (String email) {
        return !email.contains("@") || !email.contains(".com");
    }

    public boolean checkPasswordFormat (String password) {
        boolean upperFlag = false, lowerFlag = false, numberFlag = false, lengthFlag = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                upperFlag = true;
            }
            if (Character.isLowerCase(ch)) {
                lowerFlag = true;
            }
            if (Character.isDigit(ch)) {
                numberFlag = true;
            }
        }

        if (upperFlag && lowerFlag && numberFlag && password.length() >= 8) {
            return false;
        } else {
          return true;
        }

    }
}
