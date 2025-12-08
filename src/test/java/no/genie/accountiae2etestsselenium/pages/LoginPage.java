package no.genie.accountiae2etestsselenium.pages;

import no.genie.accountiae2etestsselenium.elements.LoginPageElements;

import static no.genie.accountiae2etestsselenium.helpers.PropertiesHelper.*;
import static no.genie.accountiae2etestsselenium.keywords.WebUI.*;

public class LoginPage extends LoginPageElements {

    public void loginInvalidEmail(String email, String password) {
        openURL(getValue("url"));
        //Kểm tra message thông báo lỗi khi sai email
        sendKeyToElement(emailTextbox, email);
        sendKeyToElement(passwordTextbox, password);
        clickElement(loginButton);

    }

}
