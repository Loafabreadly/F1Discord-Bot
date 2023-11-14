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
     * @param args Requires 1, with an option to supply a second arg to set the bot status code
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

            if (args.length == 2) {
                logger.info("Setting bot status to match Git Commit of " + args[1]);
                api.updateActivity("v." + args[1]);
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
                    .setVerbose(true)
                    .build();

            logger.info("Registering our Slash Commands");
            kc.registerObject(new PingCmd());
            kc.registerObject(new RedButtonCmd());
            kc.registerObject(new NicoCmd());
            kc.registerObject(new RaceCmd());
            kc.registerObject(new StandingsCmd());
            kc.registerObject(new ConstructorCmd());
            kc.registerObject(new CircuitCmd());

            logger.info("Populating the Constructor ID List");
            ErgastAPI.populateConstructorIdList();
            logger.info("List now contains " + Constants.CONSTRUCTORIDS.size() + " constructors");
            logger.info("Populating the Circuit ID List");
            ErgastAPI.populateCircuitIdList();
            logger.info("List now contains " + Constants.CIRCUITIDS.size() + " circuits");
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