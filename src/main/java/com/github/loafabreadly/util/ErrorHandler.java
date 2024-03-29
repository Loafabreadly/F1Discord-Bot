package com.github.loafabreadly.util;

import com.github.loafabreadly.Constants;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class ErrorHandler {

    public static EmbedBuilder embedError(Exception ex) {
        ex.printStackTrace();
        return new EmbedBuilder()
                .setAuthor(Constants.BOTNAME)
                .setColor(Constants.ERROR_COLOR)
                .setUrl("https://github.com/Loafabreadly/F1Discord-Bot/issues/new/choose")
                .setTitle("We hit an error while replying!")
                .addField("Stack Trace", ex.getMessage())
                .addInlineField("Submit an issue!", "Submit an issue on my Github and I will take a look")
                .addInlineField("Link", "https://github.com/Loafabreadly/F1Discord-Bot/issues/new/choose");
    }
}
