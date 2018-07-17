public class xPathGmail {

    static String gmailURL = "https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/";
    static String userEmail = "//input[@type='email']";
    static String usernameNext = "//*[contains(@class, 'RveJvd') and ./text()='Next']";
    static String userPassword = "//input[@type='password']";
    static String searchMail = "//button[@aria-label='Search Mail']";
    static String authenticationMail = "(//span[@name='Steam Support'])[1]";
    static String authenticationCode = "(//span[contains(@style, 'font-size:24px')])[2]";
    static String inbox = "//a[@title='Inbox']";
    static String signOutOptions = "//a[@class='gb_b gb_db gb_R']";
    static String signOut = "//a[@target='_top' and text()='Sign out']";

}
