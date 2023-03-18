package com.github.loafabreadly.util;

public class NoSuchTeamException extends Exception {
    public NoSuchTeamException() {
        super("No such team with the given Constructor ID!");
    }
}
