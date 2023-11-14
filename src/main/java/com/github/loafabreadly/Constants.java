package com.github.loafabreadly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public final class Constants {

    /*
    The color to use on all embeds
     */
    public static final Color TED_RED = new Color(230, 0, 43);

    /*
    The color to use when we hit an error response
     */
    public static final Color ERROR_COLOR = Color.red;

    /*
     LoafaBreadly's Discord ID for Bot Ownership purposes
     */
    public static final Long OWNER_ID = 121795270806601730L;

    /*
    The F1 Race Data API Endpoint URL
     */
    public static final String ERGASTAPIURL = "http://ergast.com/api/f1/";

    /*
    The Bot's nickname
     */
    public static final String BOTNAME = "Kravitz's Notebook";

    /*
    Used to set bot status if a git commit is not provided
     */
    public static final String VERSION = "Beta";

    /*
    The img to use in embeds for the bot
     */
    public static final String BOTICON = "https://i.imgur.com/BtcVPHP.png";

    public static final List<String> CONSTRUCTORIDS = new ArrayList<>();

    public static final List<String> CIRCUITIDS = new ArrayList<>();
}
