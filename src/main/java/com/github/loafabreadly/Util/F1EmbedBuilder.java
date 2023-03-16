package com.github.loafabreadly.Util;

import com.github.loafabreadly.Constants;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class F1EmbedBuilder extends EmbedBuilder {
    /**
     * Sets up a consistent Embedded message format
     */
    public F1EmbedBuilder() {
        super();
        setAuthor(Constants.BOTNAME);
        setThumbnail(Constants.BOTICON);
        setColor(Constants.TED_RED);
    }
}
