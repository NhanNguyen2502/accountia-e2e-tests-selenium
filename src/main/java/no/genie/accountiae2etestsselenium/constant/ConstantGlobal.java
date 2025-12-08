package no.genie.accountiae2etestsselenium.constant;

import static no.genie.accountiae2etestsselenium.helpers.PropertiesHelper.*;

public class ConstantGlobal {
    static {
        getAllFiles();
    }

    public static final String URL = getValue("url");
    public static final Boolean HEADLESS = Boolean.parseBoolean(getValue("headless"));
}
