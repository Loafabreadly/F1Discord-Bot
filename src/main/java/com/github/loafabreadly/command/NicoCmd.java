package com.github.loafabreadly.command;

import me.koply.kcommando.internal.annotations.HandleSlash;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;

public class NicoCmd implements Command {

    @Override
    @HandleSlash(name = "nico",
            desc = "Is Hamilton's career over?",
            global = true)
    public void run(SlashCommandCreateEvent event) {
        SlashCommandInteraction e = event.getSlashCommandInteraction();
        e.getChannel().get().sendMessage("<:F1 Fan Paddock:920009218801668156>").join();
    }
}
