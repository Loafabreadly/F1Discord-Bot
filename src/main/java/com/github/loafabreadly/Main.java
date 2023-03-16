package com.github.loafabreadly;

import com.github.loafabreadly.Command.*;
import me.koply.kcommando.KCommando;
import me.koply.kcommando.integration.impl.javacord.JavacordIntegration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.interaction.SlashCommand;

import java.util.Set;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    /**
     * The Bot Entrypoint
     * @param args Requires 1, with an option to supply a second arg to set the bot status code
     */
    public static void main(String[] args) {
        System.setProperty("log4j.configurationFile", "/src/main/resources/log4j2.xml");
        logger.info("Starting Bot...\n");
        logger.trace("logging in with: " + args[0] + "\n");

        //Login
        try {
            DiscordApi api = new DiscordApiBuilder()
                    .setToken(args[0])
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

            /*
            logger.info("Clearing past Global Slash Commands");
            Set<SlashCommand> globalCommands = api.getGlobalSlashCommands().get();
            for (SlashCommand cmd: globalCommands) {
                logger.debug("Cleared out " + cmd.getName());
                cmd.delete().join();
            }

            */
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
            kc.registerObject(new RaceCmd(logger));
            kc.registerObject(new StandingsCmd(logger));
            kc.registerObject(new ConstructorCmd(logger));
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}