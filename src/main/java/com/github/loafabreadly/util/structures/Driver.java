package com.github.loafabreadly.util.structures;

import lombok.Data;

import java.net.URL;
import java.util.Date;
@Data
public class Driver {
    private String givenName;
    private  String familyName;
    private  String driverId;
    private  String permanentNumber;
    private  String code;
    private  URL url;
    private  Date dateOfBirth;
    private  String nationality;
}

