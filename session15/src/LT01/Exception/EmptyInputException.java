package LT01.Exception;

import java.util.NoSuchElementException;

public class EmptyInputException extends RuntimeException {
    public EmptyInputException(String message) {
        super(message);
    }
}
