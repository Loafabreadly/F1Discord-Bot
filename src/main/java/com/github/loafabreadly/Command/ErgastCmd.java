package com.github.loafabreadly.Command;

import com.github.loafabreadly.Constants;
import me.koply.kcommando.internal.OptionType;
import me.koply.kcommando.internal.annotations.HandleSlash;
import me.koply.kcommando.internal.annotations.Option;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.SlashCommandInteractionOption;
import org.javacord.api.interaction.callback.InteractionFollowupMessageBuilder;



public class ErgastCmd implements Command {

    private final Logger logger;

    public ErgastCmd (Logger l) {
        logger = l;
    }

    @Override
    @HandleSlash(name = "race",
            desc = "Poll for F1 Data",
            global = true,
            options = {
            @Option(name = "racenum", desc = "The race number you are interested in", type = OptionType.INTEGER),
            @Option(name = "season", desc = "The season of the race", type = OptionType.INTEGER)
            })
    public void run(SlashCommandCreateEvent event) {
        SlashCommandInteraction e = event.getSlashCommandInteraction();
        e.respondLater();
        InteractionFollowupMessageBuilder response = e.createFollowupMessageBuilder();
        SlashCommandInteractionOption seasonYear = e.getArgumentByName("season").get();
        SlashCommandInteractionOption raceNum = e.getArgumentByName("racenum").get();
        int year = (int) Math.round(seasonYear.getDecimalValue().get());
        int race = (int) Math.round(raceNum.getDecimalValue().get());
        String callURL = Constants.ERGASTAPIURL + year +"/" + race + "/results";
        logger.info("I was asked to make a call to: " + callURL);
        response.append("This will work at a later timer :)").send();
    }
}
