public class xPathGmail {

    static String gmailURL = "https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/";
    static String userEmail = "//input[@type='email']";
    static String usernameNext = "//*[contains(@class, 'RveJvd') and ./text()='Next']";
    static String passwordNext = "//div[@id='passwordNext']";
    static String userPassword = "//input[@type='password']";
    static String authenticationMail = "(//span[@email='noreply@steampowered.com'])[1]";
    static String authenticationCode = "(//span[contains(@style, 'font-size:24px')])[2]";
    static String signOutOptions = "//*[contains(@href, 'SignOutOptions')]";
    static String signOut = "//a[@target='_top' and text()='Sign out']";

}
