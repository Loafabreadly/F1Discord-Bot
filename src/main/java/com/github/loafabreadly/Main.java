package com.github.loafabreadly;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.event.Event;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.callback.InteractionImmediateResponseBuilder;
import org.javacord.api.interaction.callback.InteractionMessageBuilder;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    /* Main Bot class
    /   args[0] = Bot API Token
    /   args[1] = Git commit code for version
     */
    public static void main(String[] args) {
        System.setProperty("log4j.configurationFile", "/src/main/resources/log4j2.xml");
        logger.info("Starting Bot...\n");
        logger.info("logging in with: " + args[0] + "\n");

        // System.out.println("Logging in with Token: " + args[0] + "\n\n");

        //Login
        try {
            DiscordApi api = new DiscordApiBuilder()
                    .setToken(args[0])
                    .addIntents(Intent.MESSAGE_CONTENT)
                    .login().join();

            logger.info("Setting bot status to match Git Commit of " + args[1]);
            api.updateActivity("v." + args[1]);

            //Main message event listener
            api.addMessageCreateListener(event -> {
                if (event.getMessageContent().equalsIgnoreCase("!nico")) {
                    event.getChannel().sendMessage(":nicooo:");
                }
            });

            //setting up our slash command
            SlashCommand pingCmd = SlashCommand.with("ping", "Returns Pong")
                    .createGlobal(api)
                    .join();

            //setting up the response to our slash command
            api.addSlashCommandCreateListener(event -> {
                SlashCommandInteraction inter = event.getSlashCommandInteraction();
                if (inter.getFullCommandName().equals("ping")) {
                    event.getInteraction()
                            .createImmediateResponder()
                            .setContent("Pong!")
                            .respond();
                }
            });

            logger.info(api.getCustomEmojis());
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
