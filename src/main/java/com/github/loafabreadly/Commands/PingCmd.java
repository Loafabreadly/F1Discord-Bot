package com.github.loafabreadly.Commands;

import me.koply.kcommando.internal.OptionType;
import me.koply.kcommando.internal.annotations.HandleSlash;
import me.koply.kcommando.internal.annotations.Option;
import org.javacord.api.interaction.SlashCommandInteraction;

public class PingCmd {
    @HandleSlash(name = "Pong", desc = "Responds with Ping via SlashCommand", global = true, options = @Option(type = OptionType.STRING, name = "name", required = true))
    public void PingCommand(SlashCommandInteraction e) {
        e.createImmediateResponder()
                .setContent("Well hello there!")
                .setContent("You must be " + e.getOptionByName("name"))
                .setContent("You had entered: " + e.getFullCommandName())
                .respond();
    }
}
