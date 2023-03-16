package com.github.loafabreadly.Command;

import com.github.loafabreadly.Main;
import com.github.loafabreadly.Util.ErgastAPI;
import com.github.loafabreadly.Util.ErgastHandler;
import com.github.loafabreadly.Util.ErrorHandler;
import com.github.loafabreadly.Util.F1EmbedBuilder;
import com.github.loafabreadly.Util.Structures.Races;
import lombok.NonNull;
import me.koply.kcommando.internal.OptionType;
import me.koply.kcommando.internal.annotations.HandleSlash;
import me.koply.kcommando.internal.annotations.Option;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.callback.InteractionFollowupMessageBuilder;

import java.util.List;

public class ConstructorCmd implements Command {
    private final @NonNull Logger logger = LogManager.getLogger(Main.class.getName());

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
            @NonNull List<Races> constructorResults = ErgastAPI.getData(team);

            response.addEmbed(new F1EmbedBuilder()
                            .addField("First Race", constructorResults.get(0).getRaceName() + " - " + constructorResults.get(0).getDate())
                            .addField("Total points earned", Double.toString(ErgastHandler.getConstructorTotalPoints(constructorResults)) + " - " + constructorResults.size() + " races")
                            .addField("Most Recent Race", constructorResults.get(constructorResults.size()-1).getRaceName()))
                        .send().join();
        } catch (Exception ex) {
            logger.error(ex.toString());
            response.addEmbed(ErrorHandler.embedError(ex)).send().join();
        }
    }
}
