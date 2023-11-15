package com.github.loafabreadly.util;

public class NoSuchCircuitException extends Exception {
    public NoSuchCircuitException() {
        super("No such team with the given Circuit ID!");
    }
}
