package com.github.loafabreadly;

import com.github.loafabreadly.Commands.PingCmd;
import me.koply.kcommando.KCommando;
import me.koply.kcommando.integration.impl.javacord.JavacordIntegration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.interaction.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    /* Main Bot class
    /   args[0] = Bot API Token
    /   args[1] = Git commit code for version
     */
    public static void main(String[] args) {
        System.setProperty("log4j.configurationFile", "/src/main/resources/log4j2.xml");
        logger.info("Starting Bot...\n");
        logger.trace("logging in with: " + args[0] + "\n");

        String apiURL = "https://ergast.com/api/f1/";
        String[] memes = {
                "https://i.redd.it/z3il2mpdc1191.jpg",
                "https://i.redd.it/o6rn6l8jloc91.jpg",
                "https://i.redd.it/b5iysi3arse91.jpg",
                "https://i.redd.it/1psunhykc0081.jpg",
                "https://i.redd.it/jzcmy8ui4fz71.png",
                "https://i.redd.it/uijw6e4sez381.jpg",
                "https://i.redd.it/ccooaodlf9081.jpg"
        };

        //Login
        try {
            DiscordApi api = new DiscordApiBuilder()
                    .setToken(args[0])
                    .addIntents(Intent.MESSAGE_CONTENT)
                    .login().join();

            logger.info("Setting bot status to match Git Commit of " + args[1]);
            api.updateActivity("v." + args[1]);

            JavacordIntegration jci = new JavacordIntegration(api);
            KCommando kc = new KCommando(jci)
                    .addPackage(Main.class.getPackageName())
                    .setReadBotMessages(false)
                    .setPrefix("/")
                    .build();

            //Sending back our custom emoji
            api.addMessageCreateListener(event -> {
                if (event.getMessageContent().equalsIgnoreCase("!nico")) {
                    event.getChannel().sendMessage("<:F1 Fan Paddock:920009218801668156>");
                    logger.debug("Sent the Nico meme");
                }
            });

            Set<SlashCommandBuilder> builders = new HashSet<>();
            //Build out the ping cmd
            builders.add(new SlashCommandBuilder()
                    .setName("ping")
                    .setDescription("Returns Pong"));
            //Build out the Skyq redbutton meme cmd
            builders.add(new SlashCommandBuilder()
                    .setName("redbutton")
                    .setDescription("Are you a Sky Q Customer?"));
            //build out the F1 Race data cmd
            builders.add(new SlashCommandBuilder()
                    .setName("f1")
                    .setDescription("Returns F1 Race data using ergast")
                    .setOptions(
                            Arrays.asList(
                            SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND_GROUP, "ergast" ,"ergast API Data",
                                    Arrays.asList(
                                            SlashCommandOption.createWithOptions(SlashCommandOptionType.SUB_COMMAND, "race", "Outputs race specific data",
                                                    Arrays.asList(
                                                            SlashCommandOption.create(SlashCommandOptionType.DECIMAL, "season", "Enter the year of the F1 season", true),
                                                            SlashCommandOption.create(SlashCommandOptionType.DECIMAL, "race", "The number or name of the race", true)
                                                    )))))));
            //register our command
            api.bulkOverwriteGlobalApplicationCommands(builders).join();


        //setting up the response to our Ping/Pong slash command
            api.addSlashCommandCreateListener(event -> {
                SlashCommandInteraction inter = event.getSlashCommandInteraction();
                if (inter.getUser().isBot()) {
                    return;
                }
                    // The first arg by index 0 is f1
                logger.debug("We had an interaction");
                logger.debug("We received: " + inter.getCommandName());
                    if (inter.getCommandName().equals("f1")) {
                        SlashCommandInteractionOption seasonYear = inter.getArgumentByName("season").get();
                        SlashCommandInteractionOption raceNum = inter.getArgumentByName("race").get();
                        int year = (int) Math.round(seasonYear.getDecimalValue().get());
                        int race = (int) Math.round(raceNum.getDecimalValue().get());
                        String callURL = apiURL + year + "/" + race + "/results";
                        logger.info("I was asked to attempt to call " + callURL);
                        event.getInteraction()
                                .createImmediateResponder()
                                .setContent(callURL)
                                .respond();
                    }
                if (inter.getFullCommandName().equals("redbutton")) {
                    event.getInteraction()
                            .createImmediateResponder()
                            .setContent(getMemeLink(memes))
                            .respond();
                }
                //Our ping cmd
                if (inter.getFullCommandName().equals("ping")) {
                    event.getInteraction()
                            .createImmediateResponder()
                            .setContent("Pong!")
                            .respond();
                    logger.debug("Responded to Ping command");
                }
                else if (inter.getFullCommandName().equals("f1 race")) {

                }

            });
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public static String getMemeLink(String [] links) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(links.length);
        return links[randomIndex];
    }
}
