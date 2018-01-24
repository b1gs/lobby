package com.example.lobby.exception;

/**
 * Created by ovolkovskyi on 24.01.2018.
 */
public class TooMuchProfanityException extends RuntimeException {


    public TooMuchProfanityException(String message ) {
        super(message);
    }
}
