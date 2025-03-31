package ru.korostelev.WalkMoreFatMan.controller.exceptions;

public class NotRegisteredException extends RuntimeException {
    public NotRegisteredException(String message) {
        super(message);
    }
}
