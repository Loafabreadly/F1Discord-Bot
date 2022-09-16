package com.github.loafabreadly;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

    public static void main(String[] args) {
        System.out.println("Logging in with Token: " + args[0] + "\n\n");
        DiscordApi api = new DiscordApiBuilder().setToken(args[0]).login().join();

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });
    }
}
