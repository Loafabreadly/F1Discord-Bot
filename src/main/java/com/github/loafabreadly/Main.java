package com.github.loafabreadly;

import com.github.loafabreadly.command.*;
import com.github.loafabreadly.util.ErgastAPI;
import me.koply.kcommando.KCommando;
import me.koply.kcommando.integration.impl.javacord.JavacordIntegration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

import java.awt.color.ICC_ColorSpace;
import java.util.Objects;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    /**
     * The Bot Entrypoint
     * @param args option 1, will set the bot status to match the git commit. Default is internal version of CONSTANTS.VERSION
     */
    public static void main(String[] args) {
        System.setProperty("log4j.configurationFile", "/src/main/resources/log4j2.xml");
        logger.info("Starting Bot...\n");
        logger.info("Attempting to find the Discord Token");
        String token = getToken();
        logger.trace("logging in with: " + token + "\n");

        //Login
        try {
            DiscordApi api = new DiscordApiBuilder()
                    .setToken(token)
                    .addIntents(Intent.MESSAGE_CONTENT)
                    .addIntents(Intent.GUILDS)
                    .login().join();

            if (args.length == 1) {
                logger.info("Setting bot status to match Git Commit of " + args[0]);
                api.updateActivity("v." + args[0]);
            } else {
                logger.info("Setting bot status to match internal version of " + Constants.VERSION);
                api.updateActivity("v." + Constants.VERSION);
            }

            JavacordIntegration jci = new JavacordIntegration(api);
            KCommando kc = new KCommando(jci)
                    .addPackage(Command.class.getPackageName())
                    .setReadBotMessages(false)
                    .setPrefix("/")
                    .setOwners(Constants.OWNER_ID)
                    .setVerbose(false);
                    //.build();


            logger.info("Registering our Slash Commands");
            logger.info("This can take a while depending on the amount to register");
            kc.registerObject(new PingCmd());
            logger.debug("Registered PingCmd");
            kc.registerObject(new RedButtonCmd());
            logger.debug("Registered RedButtonCmd");
            kc.registerObject(new NicoCmd());
            logger.debug("Registered NicoCmd");
            kc.registerObject(new RaceCmd());
            logger.debug("Registered RaceCmd");
            kc.registerObject(new StandingsCmd());
            logger.debug("Registered StandingCmd");
            kc.registerObject(new ConstructorCmd());
            logger.debug("Registered ConstructorCmd");
            kc.registerObject(new CircuitCmd());
            logger.debug("Registered CircuitCmd");
            kc.registerObject(new ListCircuitsCmd());
            logger.debug("Registered ListCircuitsCmd");
            kc.registerObject(new ListConstructorsCmd());
            logger.debug("Registered ListConstructorsCmd");

            kc.build();

            logger.info("Populating the Constructor ID List");
            ErgastAPI.populateConstructorIdList();
            logger.info("List now contains " + Constants.CONSTRUCTORIDS.size() + " constructors");
            logger.info("Populating the Circuit ID List");
            ErgastAPI.populateCircuitIdList();
            logger.info("List now contains " + Constants.CIRCUITIDS.size() + " circuits");

            logger.info("Bot is now ready to receive commands");
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private static String getToken() {
        String token = System.getenv("DISCORD_TOKEN");
            logger.info("Env var was defined for token!");
            return Objects.requireNonNull(token);
        }
}