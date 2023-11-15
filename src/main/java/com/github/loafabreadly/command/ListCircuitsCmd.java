package com.github.loafabreadly.command;

import com.github.loafabreadly.Constants;
import me.koply.kcommando.internal.OptionType;
import me.koply.kcommando.internal.annotations.HandleSlash;
import me.koply.kcommando.internal.annotations.Option;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.callback.InteractionFollowupMessageBuilder;
import org.javacord.api.interaction.callback.InteractionMessageBuilder;

public class ListCircuitsCmd implements Command {
    @Override
    @HandleSlash(name = "listcircuits",
            desc = "Lists out the possible Circuit IDs to use with /circuit",
            global = true)
    public void run(SlashCommandCreateEvent event) {
        SlashCommandInteraction e = event.getSlashCommandInteraction();
        StringBuilder circuitList = new StringBuilder();
        for (String circuitid : Constants.CIRCUITIDS) {
            circuitList.append(circuitid).append(",");
        }
        e.createImmediateResponder()
                .append("The below Circuit IDs are valid inputs for use with /circuit")
                .appendCode("json", circuitList.toString())
                .respond();
    }
}
