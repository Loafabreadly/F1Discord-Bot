package com.github.loafabreadly;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class BotMain {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        DiscordApi api = new DiscordApiBuilder().setToken(args[0]).login().join();

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });
    }
}
