package com.github.loafabreadly.Command;

import com.github.loafabreadly.Constants;
import me.koply.kcommando.internal.OptionType;
import me.koply.kcommando.internal.annotations.HandleSlash;
import me.koply.kcommando.internal.annotations.Option;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.callback.InteractionFollowupMessageBuilder;

import java.util.Random;

public class RedButtonCmd implements Command {

    String[] memes = {
            "https://i.redd.it/z3il2mpdc1191.jpg", "https://i.redd.it/o6rn6l8jloc91.jpg", "https://i.redd.it/b5iysi3arse91.jpg",
            "https://i.redd.it/1psunhykc0081.jpg", "https://i.redd.it/jzcmy8ui4fz71.png", "https://i.redd.it/uijw6e4sez381.jpg",
            "https://i.redd.it/ccooaodlf9081.jpg", "https://i.redd.it/nt9cv21aizla1.jpg", "https://i.redd.it/dfp62w3fz8491.png",
            "https://i.redd.it/uw8v66r7qf291.jpg", "https://i.redd.it/ggvuvk9besla1.jpg", "https://i.redd.it/li3whjjkfu091.jpg",
            "https://i.redd.it/w21f7kuq5do81.jpg", "https://i.imgur.com/jV5epk9.jpg", "https://i.redd.it/rtwiz0wsgot91.jpg"
    };
    @Override
    @HandleSlash(name = "redbutton", desc = "Are you a SkyQ or Sky Glass Customer?", global = true)
    public void run(SlashCommandCreateEvent event) {
        SlashCommandInteraction e = event.getSlashCommandInteraction();
        e.respondLater();
        InteractionFollowupMessageBuilder response = e.createFollowupMessageBuilder();

        response.addEmbed(new EmbedBuilder()
                .setAuthor(Constants.BOTNAME)
                .setColor(Constants.TED_RED)
                .setImage(getMemeLink(memes))
                .setFooter(Constants.VERSION))
                .send();
    }

    private static String getMemeLink(String [] links) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(links.length);
        return links[randomIndex];
    }
}
