package com.github.loafabreadly.Command;

import me.koply.kcommando.internal.OptionType;
import me.koply.kcommando.internal.annotations.HandleSlash;
import me.koply.kcommando.internal.annotations.Option;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;

public class PingCmd implements Command {
    @Override
    @HandleSlash(name = "pong", desc = "Responds with Ping via SlashCommand", options = @Option(type = OptionType.STRING, name = "name", required = true), global = true)
    public void run(SlashCommandCreateEvent event) {
        SlashCommandInteraction e = event.getSlashCommandInteraction();
        e.createImmediateResponder()
                .setContent("Well hello there!")
                .append("\nYou must be " + e.getArgumentByName("name").get().getStringValue().get())
                .append("\nYou had entered: " + e.getCommandName())
                .respond();
    }
}
