package com.github.loafabreadly.command;

import com.github.loafabreadly.Constants;
import com.github.loafabreadly.util.F1EmbedBuilder;
import me.koply.kcommando.internal.annotations.HandleSlash;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.callback.InteractionFollowupMessageBuilder;

import java.util.Random;

public class RedButtonCmd implements Command {

    final String[] memes = {
            "https://i.redd.it/z3il2mpdc1191.jpg", "https://i.redd.it/o6rn6l8jloc91.jpg", "https://i.redd.it/b5iysi3arse91.jpg",
            "https://i.redd.it/1psunhykc0081.jpg", "https://i.redd.it/jzcmy8ui4fz71.png", "https://i.redd.it/uijw6e4sez381.jpg",
            "https://i.redd.it/ccooaodlf9081.jpg", "https://i.redd.it/nt9cv21aizla1.jpg", "https://i.redd.it/dfp62w3fz8491.png",
            "https://i.redd.it/uw8v66r7qf291.jpg", "https://i.redd.it/ggvuvk9besla1.jpg", "https://i.redd.it/li3whjjkfu091.jpg",
            "https://i.redd.it/w21f7kuq5do81.jpg", "https://i.imgur.com/jV5epk9.jpg", "https://i.redd.it/rtwiz0wsgot91.jpg",
            "https://img.ifunny.co/images/df5f53343ee06f643e778d859839776f74107a6b508c124369e5cc77a5fc9be2_1.jpg", "https://img.ifunny.co/images/d525a47b4dab56b74e92e46694057ac9422bb1e537d1aaf15f158d88517a8083_1.jpg",
            "https://img.ifunny.co/images/a61bef2b2b2e7e119696e0f2742590a6862ff3040a39caf5f41273214dd9283a_1.jpg", "https://i.redd.it/tff2h7tavc281.png",
            "https://i.redd.it/x120siz7ky081.png", "https://img.ifunny.co/images/8cbf5d1f480cf6f918c37972b9760ec339bcc67bea8906e2c0d46245a735d625_1.jpg"
    };
    @Override
    @HandleSlash(name = "redbutton",
            desc = "Are you a SkyQ or Sky Glass Customer?",
            global = true)
    public void run(SlashCommandCreateEvent event) {
        SlashCommandInteraction e = event.getSlashCommandInteraction();
        e.respondLater();
        InteractionFollowupMessageBuilder response = e.createFollowupMessageBuilder();

        response.addEmbed(new F1EmbedBuilder()
                .setImage(getMemeLink(memes)))
                .send();
    }

    private static String getMemeLink(String [] links) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(links.length);
        return links[randomIndex];
    }
}
