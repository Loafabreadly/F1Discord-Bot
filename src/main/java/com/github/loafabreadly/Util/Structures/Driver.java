package com.github.loafabreadly.Util.Structures;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.Date;

public class Driver {
    private @Getter @Setter String givenName;
    private @Getter @Setter String familyName;
    private @Getter @Setter String driverId;
    private @Getter @Setter String permanentNumber;
    private @Getter @Setter String code;
    private @Getter @Setter URL url;
    private @Getter @Setter Date dateOfBirth;
    private @Getter @Setter String nationality;
}

