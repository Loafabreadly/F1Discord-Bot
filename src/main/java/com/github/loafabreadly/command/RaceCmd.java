package com.github.loafabreadly.command;


import com.github.loafabreadly.Main;
import com.github.loafabreadly.util.ErgastAPI;
import com.github.loafabreadly.util.ErgastObjectMapper;
import com.github.loafabreadly.util.ErrorHandler;
import com.github.loafabreadly.util.F1EmbedBuilder;
import com.github.loafabreadly.util.structures.*;
import lombok.NonNull;
import me.koply.kcommando.internal.OptionType;
import me.koply.kcommando.internal.annotations.HandleSlash;
import me.koply.kcommando.internal.annotations.Option;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.callback.InteractionFollowupMessageBuilder;

import java.util.List;


public class RaceCmd implements Command {

    private final @NonNull Logger logger = LogManager.getLogger(Main .class.getName());


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
            ErgastJsonReply data = om.readValue(ErgastAPI.getCircuitData(season, raceNum), ErgastJsonReply.class);
            Races raceData = data.getMrData().getRaceTable().getRaces().get(0);
            Circuit circuitData = raceData.getCircuit();
            List<DriverResult> driverResults = raceData.getDriverResults();

            response.addEmbed(new F1EmbedBuilder()
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
                            .addInlineField("Fastest Laptime/Lap", getFastestLapTime(driverResults.get(0)) +
                                    "/" + getFastestLap(driverResults.get(0)))
                            .addInlineField("Second Place", driverResults.get(1).getDriver().getGivenName() +
                                    " " +
                                    driverResults.get(1).getDriver().getFamilyName() +
                                    " - " +
                                    driverResults.get(1).getConstructor().getName())
                            .addInlineField("Delta/Laps", driverResults.get(1).getTime().getTime() +
                                    "/" + driverResults.get(1).getLaps())
                            .addInlineField("Fastest Laptime/Lap", getFastestLapTime(driverResults.get(1)) +
                                    "/" + getFastestLap(driverResults.get(1)))
                            .addInlineField("Third Place",driverResults.get(2).getDriver().getGivenName() +
                                    " " +
                                    driverResults.get(2).getDriver().getFamilyName() +
                                    " - " +
                                    driverResults.get(2).getConstructor().getName())
                            .addInlineField("Delta/Laps", driverResults.get(2).getTime().getTime() +
                                    "/" + driverResults.get(2).getLaps())
                            .addInlineField("Fastest Laptime/Lap", getFastestLapTime(driverResults.get(2)) +
                                    "/" + getFastestLap(driverResults.get(2))))
                    .send().join();
        } catch (Exception ex) {
            logger.error(ex.toString());
            response.addEmbed(ErrorHandler.embedError(ex)).send().join();
        }
    }

    /**
     * Used to handle pre-2004 dates where FastestLap data is not available
     * @param d DriverResults
     * @return The String version of the Fastest Lap Time, or No Data
     */
    private String getFastestLapTime(DriverResult d) {
        try {
            return d.getFastestLap().getTime().getTime();
        } catch (NullPointerException e) {
            return "No Data";
        }
    }

    /**
     * Used to handle pre-2004 dates where FastestLap data is not available
     * @param d DriverResults
     * @return The String version of the Lap at which the Fastest Time was set, or No Data
     */
    private String getFastestLap(DriverResult d) {
        try {
            return d.getFastestLap().getLap();
        } catch (NullPointerException e) {
            return "No Data";
        }
    }
}