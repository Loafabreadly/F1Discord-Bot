package com.github.loafabreadly.Command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.loafabreadly.Constants;
import com.github.loafabreadly.Util.ErgastAPI;
import com.github.loafabreadly.Util.Structures.DriverResult;
import com.github.loafabreadly.Util.Structures.RaceResults;
import lombok.NonNull;
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

    private final @NonNull Logger logger;

    public RaceCmd(Logger l) {
        logger = l;
    }

    @Override
    @HandleSlash(name = "race",
            desc = "Poll for F1 Data",
            global = true,
            options = {
            @Option(name = "season", desc = "The season of the race", type = OptionType.INTEGER, required = true),
            @Option(name = "racenum", desc = "The race number you are interested in", type = OptionType.INTEGER, required = true)
            })
    public void run(SlashCommandCreateEvent event) throws JsonProcessingException {
        SlashCommandInteraction e = event.getSlashCommandInteraction();
        e.respondLater();
        InteractionFollowupMessageBuilder response = e.createFollowupMessageBuilder();

        try {
            int raceNum = e.getArgumentDecimalValueByName("racenum").get().intValue();
            int season = e.getArgumentDecimalValueByName("season").get().intValue();

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
        } catch (Exception ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
            response.addEmbed(new EmbedBuilder()
                    .setAuthor(Constants.BOTNAME)
                    .setColor(Constants.ERROR_COLOR)
                    .addField("Stack Trace", ex.toString())
                    .setTitle("We hit an error while replying!"))
                    .send().join();
        }
    }
}