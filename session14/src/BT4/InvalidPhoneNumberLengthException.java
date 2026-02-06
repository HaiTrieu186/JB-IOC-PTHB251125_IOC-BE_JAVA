package BT4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvalidPhoneNumberLengthException extends Exception {
    private static final String validateNumber="^[0-9]{10}$";

    public InvalidPhoneNumberLengthException() {
    }

    public InvalidPhoneNumberLengthException(String message) {
        super(message);
    }

    public InvalidPhoneNumberLengthException(String message, Throwable cause, String phoneNumber) {
        super(message, cause);
    }

    public static void validatePhoneNumber(String phone)throws InvalidPhoneNumberLengthException {
        if (phone.matches(".*\\s.*"))
            throw new InvalidPhoneNumberLengthException("Không được chứa khoảng trắng");

        if (phone.length() != 10)
            throw new InvalidPhoneNumberLengthException("Độ dài không hợp lệ");

        if (phone.matches(".*[^0-9].*"))
            throw new InvalidPhoneNumberLengthException("Chứa ký tự không hợp lệ");

    }
}
