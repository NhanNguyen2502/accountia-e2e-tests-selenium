package no.genie.accountiae2etestsselenium.constant;

import static no.genie.accountiae2etestsselenium.helpers.PropertiesHelper.*;

public class ConstantGlobal {
    static {
        getAllFiles();
    }

    public static final String URL = getValue("url");
    public static final Boolean HEADLESS = Boolean.parseBoolean(getValue("headless"));
    public static final int IMPLICIT_WAIT = Integer.parseInt(getValue("implicit_wait"));
    public static final int PAGE_LOAD_TIMEOUT = Integer.parseInt(getValue("page_load_timeout"));
}
