package com.github.loafabreadly;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.interaction.*;

import java.util.Arrays;

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

        String apiURL = "https://ergast.com/api/f1/";

        //Login
        try {
            DiscordApi api = new DiscordApiBuilder()
                    .setToken(args[0])
                    .addIntents(Intent.MESSAGE_CONTENT)
                    .login().join();

            logger.info("Setting bot status to match Git Commit of " + args[1]);
            api.updateActivity("v." + args[1]);

            //Sending back our custom emoji
            api.addMessageCreateListener(event -> {
                if (event.getMessageContent().equalsIgnoreCase("!nico")) {
                    event.getChannel().sendMessage("<:F1 Fan Paddock:920009218801668156>");
                    logger.debug("Sent the Nico meme");
                }
            });

            //setting up our Ping/Pong slash command
            SlashCommand pingCmd = SlashCommand.with("ping", "Returns Pong")
                    .createGlobal(api)
                    .join();


            //setting up the response to our Ping/Pong slash command
            api.addSlashCommandCreateListener(event -> {
                SlashCommandInteraction inter = event.getSlashCommandInteraction();
                SlashCommandInteractionOption seasonYear = inter.getOptions().get(0);
                SlashCommandInteractionOption raceNum = inter.getArguments().get(1);
                //Our ping cmd
                if (inter.getFullCommandName().equals("ping")) {
                    event.getInteraction()
                            .createImmediateResponder()
                            .setContent("Pong!")
                            .respond();
                    logger.debug("Responded to Ping command");
                }
                else if (inter.getFullCommandName().equals("f1 race")) {
                    int year = (int) Math.round(seasonYear.getDecimalValue().get());
                    int race = (int) Math.round(raceNum.getDecimalValue().get());
                    String callURL = apiURL + year + "/" + race + "/results";
                    logger.info("I was asked to attempt to call" + callURL);
                }

            });

            //Inital F1 Slash Command
            //called by /f1 race [season] [race number/name]
            SlashCommand f1Cmd =
                    SlashCommand.with("f1", "A command to retrieve F1 race data",
                            Arrays.asList(
                                SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND_GROUP, "ergast" ,"ergast API Data",
                                        Arrays.asList(
                                            SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "race", "Outputs race specific data",
                                            Arrays.asList(
                                                SlashCommandOption.create(SlashCommandOptionType.DECIMAL, "F1 Season", "Enter the year of the F1 season"),
                                                SlashCommandOption.create(SlashCommandOptionType.DECIMAL, "race", "The number or name of the race")
                                            ))))))
                    .createGlobal(api)
                    .join();
            //logger.info(api.getCustomEmojis());
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
