package com.github.loafabreadly.command;

import com.github.loafabreadly.Constants;
import com.github.loafabreadly.Main;
import com.github.loafabreadly.util.*;
import com.github.loafabreadly.util.structures.Races;
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

public class ConstructorCmd implements Command {
    private final @NonNull Logger logger = LogManager.getLogger(Main.class.getName());

    @SuppressWarnings("ConstantConditions")
    @Override
    @HandleSlash(name = "constructors",
    desc = "Provide info on F1 Constructors",
    global = true,
    options = @Option(type = OptionType.STRING, name = "team", desc = "The F1 Team(ex red_bull)", required = true))
    public void run(SlashCommandCreateEvent event) {
        SlashCommandInteraction e = event.getSlashCommandInteraction();
        e.respondLater();
        InteractionFollowupMessageBuilder response = e.createFollowupMessageBuilder();

        try {
            @NonNull String team = e.getArgumentStringValueByName("team").orElseThrow().toLowerCase();
            if (Constants.CONSTRUCTORIDS.contains(team)) {
                @NonNull List<Races> constructorResults = ErgastAPI.getData(team);

                response.addEmbed(new F1EmbedBuilder()
                                .setTitle(constructorResults.get(0).getDriverResults().get(0).getConstructor().getName())
                                .addField("First Race", constructorResults.get(0).getRaceName() + " - " + constructorResults.get(0).getDate())
                                .addField("Total points earned", ErgastHandler.getConstructorTotalPoints(constructorResults) + " points across " + constructorResults.size() + " races")
                                .addField("Most Recent Race", constructorResults.get(constructorResults.size() - 1).getRaceName() + " - " + constructorResults.get(constructorResults.size() - 1).getDate()))
                        .send().join();
            } else {
                throw new NoSuchTeamException();
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            response.addEmbed(ErrorHandler.embedError(ex)).send().join();
        }
    }
}
