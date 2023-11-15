package com.github.loafabreadly.command;

import com.github.loafabreadly.Constants;
import me.koply.kcommando.internal.annotations.HandleSlash;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;

public class ListConstructorsCmd implements Command {
    @Override
    @HandleSlash(name = "listconstructors",
            desc = "Lists out the possible Constructors IDs to use with /constructor",
            global = true)
    public void run(SlashCommandCreateEvent event) {
        SlashCommandInteraction e = event.getSlashCommandInteraction();
        StringBuilder constructorList = new StringBuilder();
        for (String cList : Constants.CONSTRUCTORIDS) {
            constructorList.append(cList).append(",").append(" ");
        }
        e.createImmediateResponder()
                .append("The below Constructor IDs are valid inputs for use with /constructor")
                .appendCode("json", constructorList.toString())
                .respond();
    }
}
