package com.github.loafabreadly.Command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.loafabreadly.Constants;
import com.github.loafabreadly.Util.ErgastAPI;
import com.github.loafabreadly.Util.Structures.DriverResult;
import com.github.loafabreadly.Util.Structures.RaceResults;
import me.koply.kcommando.internal.OptionType;
import me.koply.kcommando.internal.annotations.HandleSlash;
import me.koply.kcommando.internal.annotations.Option;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.SlashCommandInteractionOption;
import org.javacord.api.interaction.callback.InteractionFollowupMessageBuilder;

import java.util.List;


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
    public void run(SlashCommandCreateEvent event) throws JsonProcessingException {
        SlashCommandInteraction e = event.getSlashCommandInteraction();
        e.respondLater();
        InteractionFollowupMessageBuilder response = e.createFollowupMessageBuilder();
        int raceNum = e.getArgumentByName("racenum").get().getDecimalValue().get().intValue();
        int season = e.getArgumentByName("season").get().getDecimalValue().get().intValue();

        String responseJson = ErgastAPI.getData(season, raceNum);
        ObjectMapper om = new ObjectMapper();
        RaceResults raceResults = om.readValue(responseJson, RaceResults.class);
        List<DriverResult> driverResults = raceResults.getDriverResults();

        response.addEmbed(new EmbedBuilder()
                        .setColor(Constants.TED_RED)
                        .setTitle(raceResults.getRaceName())
                        .setFooter(raceResults.getWikiUrl())
                        .addField("Track Name", raceResults.getRaceName())
                        .addField("Driver Winner", driverResults.get(0).getDriver().getGivenName() + driverResults.get(0).getDriver().getFamilyName())
                        .setAuthor(Constants.BOTNAME))
                .send().join();
    }
}
