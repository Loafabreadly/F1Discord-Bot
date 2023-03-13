package com.github.loafabreadly.Util;

import com.github.loafabreadly.Constants;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class ErrorHandler {

    public static EmbedBuilder embedError(Exception ex) {
        ex.printStackTrace();
        return new EmbedBuilder()
                .setAuthor(Constants.BOTNAME)
                .setColor(Constants.ERROR_COLOR)
                .addField("Stack Trace", ex.toString())
                .setTitle("We hit an error while replying!");
    }
}
