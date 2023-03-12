package com.github.loafabreadly.Command;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.loafabreadly.Constants;
import com.github.loafabreadly.Util.ErgastAPI;
import com.github.loafabreadly.Util.ErrorHandler;
import com.github.loafabreadly.Util.Structures.*;
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
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ErgastJsonReply data = om.readValue(responseJson, ErgastJsonReply.class);
            Races raceData = data.getMrData().getRaceTable().getRaces()[0];
            Circuit circuitData = raceData.getCircuit();
            DriverResult[] driverResults = raceData.getDriverResults();

            response.addEmbed(new EmbedBuilder()
                            .setColor(Constants.TED_RED)
                            .setAuthor(Constants.BOTNAME)
                            .setThumbnail(Constants.BOTICON)
                            .setTitle(raceData.getRaceName())
                            .setFooter(raceData.getUrl().toString())
                            .addInlineField("Track Name", circuitData.getCircuitName())
                            .addInlineField("Constructor Winner", driverResults[0].getConstructor().getName())
                            .addField("First Place", driverResults[0].getDriver().getGivenName() + " " + driverResults[0].getDriver().getFamilyName())
                            .addField("Second Place", driverResults[1].getDriver().getGivenName() + " " + driverResults[1].getDriver().getFamilyName())
                            .addField("Third Place",driverResults[2].getDriver().getGivenName() + " " + driverResults[2].getDriver().getFamilyName()))
                    .send().join();
        } catch (Exception ex) {
            logger.error(ex.toString());
            response.addEmbed(ErrorHandler.embedError(ex)).send().join();
        }
    }
}