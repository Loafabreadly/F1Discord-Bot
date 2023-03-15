package com.github.loafabreadly.Command;

import com.github.loafabreadly.Util.ErgastAPI;
import com.github.loafabreadly.Util.ErgastObjectMapper;
import com.github.loafabreadly.Util.ErrorHandler;
import com.github.loafabreadly.Util.Structures.ErgastJsonReply;
import com.github.loafabreadly.Util.Structures.Races;
import lombok.NonNull;
import me.koply.kcommando.internal.OptionType;
import me.koply.kcommando.internal.annotations.HandleSlash;
import me.koply.kcommando.internal.annotations.Option;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.callback.InteractionFollowupMessageBuilder;

import java.util.List;

public class ConstructorCmd implements Command {
    private final @NonNull Logger logger;
    public ConstructorCmd(@NonNull Logger l) {
        logger = l;
    }

    @Override
    @HandleSlash(name = "constructors",
    desc = "Provide info on F1 Constructors",
    options = @Option(type = OptionType.STRING, name = "team", desc = "The F1 Team you are interested in", required = true))
    public void run(SlashCommandCreateEvent event) {
        SlashCommandInteraction e = event.getSlashCommandInteraction();
        e.respondLater();
        InteractionFollowupMessageBuilder response = e.createFollowupMessageBuilder();

        try {
            String responseJson = ErgastAPI.getData(e.getArgumentStringValueByName("team").orElseThrow().toLowerCase());
            ErgastObjectMapper om = new ErgastObjectMapper();
            @NonNull ErgastJsonReply data = om.readValue(responseJson, ErgastJsonReply.class);
            List<Races> table = data.getMrData().getRaceTable().getRaces();

        } catch (Exception ex) {
            logger.error(ex.toString());
            response.addEmbed(ErrorHandler.embedError(ex)).send().join();
        }
    }
}
