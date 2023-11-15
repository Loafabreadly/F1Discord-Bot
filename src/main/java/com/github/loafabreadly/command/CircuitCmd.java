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

public class CircuitCmd implements Command {
    private final @NonNull Logger logger = LogManager.getLogger(Main.class.getName());

    @SuppressWarnings("ConstantConditions")
    @Override
    @HandleSlash(name = "circuit",
    desc = "Provide info on F1 Circuits",
    global = true,
    options = @Option(type = OptionType.STRING, name = "circuit", desc = "The F1 Circuit ('baku')", required = true))
    public void run(SlashCommandCreateEvent event) {
        SlashCommandInteraction e = event.getSlashCommandInteraction();
        e.respondLater();
        InteractionFollowupMessageBuilder response = e.createFollowupMessageBuilder();

        try {
            @NonNull String circuit = e.getArgumentStringValueByName("circuit").orElseThrow().toLowerCase();
            if (Constants.CIRCUITIDS.contains(circuit)) {
                @NonNull List<Races> circuitResults = ErgastAPI.getCircuitData(circuit);

                response.addEmbed(new F1EmbedBuilder()
                                .setTitle(circuitResults.get(0).getCircuit().getCircuitName())
                                .addField("First Race", circuitResults.get(0).getRaceName() + " - " + circuitResults.get(0).getDate())
                                .addField("Total races ran", String.valueOf(circuitResults.size()))
                                .addField("Most Recent Race", circuitResults.get(circuitResults.size() - 1).getRaceName() + " - " + circuitResults.get(circuitResults.size() - 1).getDate()))
                        .send().join();
            } else {
                throw new NoSuchCircuitException();
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            response.addEmbed(ErrorHandler.embedError(ex)).send().join();
        }
    }
}
