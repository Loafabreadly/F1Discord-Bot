package com.github.loafabreadly.Command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.loafabreadly.Constants;
import com.github.loafabreadly.Util.ErgastAPI;
import com.github.loafabreadly.Util.ErrorHandler;
import com.github.loafabreadly.Util.Structures.DriverResult;
import com.github.loafabreadly.Util.Structures.ErgastJsonReply;
import com.github.loafabreadly.Util.Structures.MRData;
import com.github.loafabreadly.Util.Structures.RaceResults;
import lombok.NonNull;
import me.koply.kcommando.internal.OptionType;
import me.koply.kcommando.internal.annotations.HandleSlash;
import me.koply.kcommando.internal.annotations.Option;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
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
            @Option(type = OptionType.STRING, name = "season", desc = "The season of the race",  required = true),
            @Option(type = OptionType.STRING, name = "racenum", desc = "The race number you are interested in",  required = true)
            })
    public void run(SlashCommandCreateEvent event) {
        SlashCommandInteraction e = event.getSlashCommandInteraction();
        e.respondLater();
        InteractionFollowupMessageBuilder response = e.createFollowupMessageBuilder();

        try {
            int season = Integer.parseInt(e.getArgumentStringValueByName("season").get());
            int raceNum = Integer.parseInt(e.getArgumentStringValueByName("racenum").get());

            String responseJson = ErgastAPI.getData(season, raceNum);
            ObjectMapper om = new ObjectMapper();
            ErgastJsonReply data = om.readValue(responseJson, ErgastJsonReply.class);
            RaceResults raceResults = data.getMrData().getRaceTable().getRaces().get(0).getRaceResults().get(0);
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
            response.addEmbed(ErrorHandler.embedError(ex)).send().join();
        }
    }
}