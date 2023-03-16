package com.github.loafabreadly.Command;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.loafabreadly.Constants;
import com.github.loafabreadly.Util.ErgastAPI;
import com.github.loafabreadly.Util.ErgastObjectMapper;
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

    public RaceCmd(@NonNull Logger l) {
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
            int season = Integer.parseInt(e.getArgumentStringValueByName("season").orElseThrow());
            int raceNum = Integer.parseInt(e.getArgumentStringValueByName("racenum").orElseThrow());

            ErgastObjectMapper om = new ErgastObjectMapper();
            ErgastJsonReply data = om.readValue(ErgastAPI.getData(season, raceNum), ErgastJsonReply.class);
            Races raceData = data.getMrData().getRaceTable().getRaces().get(0);
            Circuit circuitData = raceData.getCircuit();
            List<DriverResult> driverResults = raceData.getDriverResults();

            response.addEmbed(new EmbedBuilder()
                            .setColor(Constants.TED_RED)
                            .setAuthor(Constants.BOTNAME)
                            .setThumbnail(Constants.BOTICON)
                            .setTitle(raceData.getRaceName())
                            .setFooter(raceData.getUrl().toString())
                            .addField("Track Name", circuitData.getCircuitName())
                            .addField("Country", circuitData.getLocation().getCountry())
                            .addField("Constructor Winner", driverResults.get(0).getConstructor().getName())
                            .addInlineField("First Place", driverResults.get(0).getDriver().getGivenName() +
                                    " " +
                                    driverResults.get(0).getDriver().getFamilyName() +
                                    " - " +
                                    driverResults.get(0).getConstructor().getName())
                            .addInlineField("Time/Laps", driverResults.get(0).getTime().getTime() +
                                    "/" + driverResults.get(0).getLaps())
                            .addInlineField("Fastest Laptime/Lap", driverResults.get(0).getFastestLap().getTime().getTime() +
                                    "/" + driverResults.get(0).getFastestLap().getLap())
                            .addInlineField("Second Place", driverResults.get(1).getDriver().getGivenName() +
                                    " " +
                                    driverResults.get(1).getDriver().getFamilyName() +
                                    " - " +
                                    driverResults.get(1).getConstructor().getName())
                            .addInlineField("Delta/Laps", driverResults.get(1).getTime().getTime() +
                                    "/" + driverResults.get(1).getLaps())
                            .addInlineField("Fastest Laptime/Lap", driverResults.get(1).getFastestLap().getTime().getTime() +
                                    "/" + driverResults.get(1).getFastestLap().getLap())
                            .addInlineField("Third Place",driverResults.get(2).getDriver().getGivenName() +
                                    " " +
                                    driverResults.get(2).getDriver().getFamilyName() +
                                    " - " +
                                    driverResults.get(2).getConstructor().getName())
                            .addInlineField("Delta/Laps", driverResults.get(2).getTime().getTime() +
                                    "/" + driverResults.get(2).getLaps())
                            .addInlineField("Fastest Laptime/Lap", driverResults.get(2).getFastestLap().getTime().getTime() +
                                    "/" + driverResults.get(2).getFastestLap().getLap())                    )
                    .send().join();
        } catch (Exception ex) {
            logger.error(ex.toString());
            response.addEmbed(ErrorHandler.embedError(ex)).send().join();
        }
    }
}