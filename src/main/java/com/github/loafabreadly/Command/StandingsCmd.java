package com.github.loafabreadly.Command;


import com.github.loafabreadly.Constants;
import com.github.loafabreadly.Util.ErgastAPI;
import com.github.loafabreadly.Util.ErgastObjectMapper;
import com.github.loafabreadly.Util.ErrorHandler;
import com.github.loafabreadly.Util.Structures.ConstructorStandings;
import com.github.loafabreadly.Util.Structures.DriverStandings;
import com.github.loafabreadly.Util.Structures.ErgastJsonReply;
import com.github.loafabreadly.Util.Structures.StandingsList;
import lombok.NonNull;
import me.koply.kcommando.internal.OptionType;
import me.koply.kcommando.internal.annotations.HandleSlash;
import me.koply.kcommando.internal.annotations.Option;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.callback.InteractionFollowupMessageBuilder;


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
                case "constructors":
                case "constructor":
                case "c": {
                    String responseJson = ErgastAPI.getConstructorStandings();
                    logger.info(responseJson);
                    ErgastObjectMapper om = new ErgastObjectMapper();
                    @NonNull StandingsList data = om.readValue(responseJson, StandingsList.class);
                    ConstructorStandings[] standings = data.getConstructorStandings();
                    response.addEmbed(constructorStandingsEmbed(standings))
                            .send().join();
                }
                case "drivers":
                case "driver":
                case "d": {
                    String responseJson = ErgastAPI.getDriverStandings();
                    logger.info(responseJson);
                    ErgastObjectMapper om = new ErgastObjectMapper();
                    @NonNull StandingsList data = om.readValue(responseJson, StandingsList.class);
                    DriverStandings[] standings = data.getDriverStandings();
                    response.addEmbed(driverStandingsEmbed(standings))
                            .send().join();
                }
                case "both":
                case "b":
                default: {
                    String responseJson = ErgastAPI.getDriverStandings();
                    logger.info(responseJson);
                    ErgastObjectMapper om = new ErgastObjectMapper();
                    @NonNull StandingsList data = om.readValue(responseJson, StandingsList.class);
                    DriverStandings[] dStandings = data.getDriverStandings();
                    response.addEmbed(driverStandingsEmbed(dStandings));

                    responseJson = ErgastAPI.getConstructorStandings();
                    data = om.readValue(responseJson, StandingsList.class);
                    ConstructorStandings[] cStandings = data.getConstructorStandings();
                    response.addEmbed(constructorStandingsEmbed(cStandings))
                            .send().join();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            response.addEmbed(ErrorHandler.embedError(ex)).send().join();
        }
    }

    /**
     * Spits our an Embeded Message Full of the Constructor Standings
     * @param standings The array of ConstructorStandings (examples/constructorStandings.json)
     * @return An EmbedBuilder to be added to an InteractionFollowupMessagebuilder
     */
    private EmbedBuilder constructorStandingsEmbed(ConstructorStandings[] standings) {
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setColor(Constants.TED_RED)
                .setAuthor(Constants.BOTNAME)
                .setThumbnail(Constants.BOTICON);
        for (ConstructorStandings standing: standings) {
            embedBuilder.addField("Constructor",
                    standing.getConstructor() + ": " + standing.getPoints() + " points - " + standing.getWins() + " wins");
        }
        return new EmbedBuilder();
    }
    /**
     * Spits our an Embded Message Full of the Scoring Drivers Standings
     * @param standings The Array of DriverStandings (examples/driverStandings.json)
     * @return An EmbedBuilder to be added to an InteractionFollowupMessagebuilder
     */
    private EmbedBuilder driverStandingsEmbed(DriverStandings[] standings) {
        EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setColor(Constants.TED_RED)
                    .setAuthor(Constants.BOTNAME)
                    .setThumbnail(Constants.BOTICON);
        for (DriverStandings standing : standings) {
            if (!standing.getPoints().equalsIgnoreCase("0")) {
                embedBuilder.addInlineField(standing.getPositionText(),
                        standing.getDriver().getGivenName() + " " + standing.getDriver().getFamilyName() +
                                " (" + standing.getPoints() + ")");
            }
        }
        return embedBuilder;
    }
}
