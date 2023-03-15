package com.github.loafabreadly.Command;



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


public class StandingsCmd implements Command {

    private final @NonNull Logger logger;
    public StandingsCmd(@NonNull Logger l) {
        logger = l;
    }


    @Override
    @HandleSlash(name = "standings",
    desc = "Outputs the Driver and/or Constructors Standings",
    global = true,
    options = @Option(type = OptionType.STRING, name = "type", desc = "'both', 'driver', 'constructor'", required = true))
    public void run(SlashCommandCreateEvent event) {
        SlashCommandInteraction e = event.getSlashCommandInteraction();
        e.respondLater();
        InteractionFollowupMessageBuilder response = e.createFollowupMessageBuilder();

        try {

            switch (e.getArgumentStringValueByName("type").orElseThrow().toLowerCase()) {
                case "constructors", "constructor", "c" -> {
                    String responseJson = ErgastAPI.getConstructorStandings();
                    logger.debug(responseJson);
                    ErgastObjectMapper om = new ErgastObjectMapper();
                    @NonNull ErgastJsonReply data = om.readValue(responseJson, ErgastJsonReply.class);
                    response.addEmbed(constructorStandingsEmbed(data.getMrData().getStandingsTable().getStandingsLists().get(0).getConstructorStandings()))
                            .send().join();
                }
                case "drivers", "driver", "d" -> {
                    String responseJson = ErgastAPI.getDriverStandings();
                    logger.debug(responseJson);
                    ErgastObjectMapper om = new ErgastObjectMapper();
                    @NonNull ErgastJsonReply data = om.readValue(responseJson, ErgastJsonReply.class);
                    response.addEmbed(driverStandingsEmbed(data.getMrData().getStandingsTable().getStandingsLists().get(0).getDriverStandings()))
                            .send().join();
                }
                case "both", "b" -> {
                    String responseJson = ErgastAPI.getDriverStandings();
                    logger.debug(responseJson);
                    ErgastObjectMapper om = new ErgastObjectMapper();
                    @NonNull ErgastJsonReply data = om.readValue(responseJson, ErgastJsonReply.class);
                    List<DriverStandings> dStandings = data.getMrData().getStandingsTable().getStandingsLists().get(0).getDriverStandings();
                    response.addEmbed(driverStandingsEmbed(dStandings));

                    responseJson = ErgastAPI.getConstructorStandings();
                    data = om.readValue(responseJson, ErgastJsonReply.class);
                    List<ConstructorStandings> cStandings = data.getMrData().getStandingsTable().getStandingsLists().get(0).getConstructorStandings();
                    response.addEmbed(constructorStandingsEmbed(cStandings))
                            .send().join();
                }
                default -> {
                    response.addEmbed(new EmbedBuilder()
                            .addField("Hmmm", "How'd you manage this?")).send().join();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            response.addEmbed(ErrorHandler.embedError(ex)).send().join();
        }
    }

    /**
     * Spits our an Embedded Message Full of the Constructor Standings
     * @param standings The array of ConstructorStandings (examples/constructorStandings.json)
     * @return An EmbedBuilder to be added to an InteractionFollowupMessageBuilder
     */
    private EmbedBuilder constructorStandingsEmbed(List<ConstructorStandings> standings) {
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setColor(Constants.TED_RED)
                .setAuthor(Constants.BOTNAME)
                .setThumbnail(Constants.BOTICON)
                .setTitle("Current Constructor Standings");
        for (ConstructorStandings standing: standings) {
            embedBuilder.addField("Constructor",
                    standing.getConstructor().getName() + ": " + standing.getPoints() + " points - " + standing.getWins() + " wins");
        }
        return embedBuilder;
    }
    /**
     * Spits our an Embedded Message Full of the Scoring Drivers Standings
     * @param standings The Array of DriverStandings (examples/driverStandings.json)
     * @return An EmbedBuilder to be added to an InteractionFollowupMessageBuilder
     */
    private EmbedBuilder driverStandingsEmbed(List<DriverStandings> standings) {
        EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setColor(Constants.TED_RED)
                    .setAuthor(Constants.BOTNAME)
                    .setThumbnail(Constants.BOTICON)
                    .setTitle("Current Driver Standings (Points Scorers only)");
        for (DriverStandings standing : standings) {
            if (!standing.getPoints().equalsIgnoreCase("0")) {
                embedBuilder.addInlineField(standing.getPositionText() + " place",
                        standing.getDriver().getGivenName() + " " + standing.getDriver().getFamilyName() +
                                " (" + standing.getPoints() + ")");
            }
        }
        return embedBuilder;
    }
}
