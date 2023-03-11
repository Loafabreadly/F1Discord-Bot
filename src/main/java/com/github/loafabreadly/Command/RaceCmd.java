package com.github.loafabreadly.Command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.loafabreadly.Constants;
import com.github.loafabreadly.Util.ErgastAPI;
import me.koply.kcommando.internal.OptionType;
import me.koply.kcommando.internal.annotations.HandleSlash;
import me.koply.kcommando.internal.annotations.Option;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.SlashCommandInteractionOption;
import org.javacord.api.interaction.callback.InteractionFollowupMessageBuilder;



public class RaceCmd implements Command {

    private final Logger logger;

    public RaceCmd(Logger l) {
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
        int raceNum = (int) Math.round(e.getArgumentByName("racenum").get().getDecimalValue().get());
        int season = (int) Math.round(e.getArgumentByName("season").get().getDecimalValue().get());

        String responseJson = ErgastAPI.getData(season, raceNum);
        ObjectMapper om = new ObjectMapper();
        om.readValue(responseJson)
    }
}
