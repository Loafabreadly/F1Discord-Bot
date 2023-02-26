package com.github.loafabreadly;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    /* Main Bot class
    /   args[0] = Bot API Token
    /   args[1] = Git commit code for version
     */
    public static void main(String[] args) {
        logger.info("Starting Bot...\n");
        logger.info("logging in with: " + args[0] + "\n");

        System.out.println("Logging in with Token: " + args[0] + "\n\n");

        //Login
        DiscordApi api = new DiscordApiBuilder()
                .setToken(args[0])
                .addIntents(Intent.MESSAGE_CONTENT)
                .login().join();

        logger.info("Setting bot status to match Git Commit of " + args[1]);
        api.updateActivity("v." + args[1]);


        //Main message event listener
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });
    }
}
