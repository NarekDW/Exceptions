package com.epam.courses.exceptions.t02;

/**
 * Date: 03.02.2017
 *
 * @author Karapetyan N.K
 */
public class KeyNotExistException extends RuntimeException {
    public KeyNotExistException() {
        super();
    }

    public KeyNotExistException(String message) {
        super(message);
    }
}
