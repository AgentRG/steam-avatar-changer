public class xPathSteam extends Main {

    static String loginButton = "//a[@class='global_action_link']";
    static String usernameTab = "//*[@id='input_username']";
    static String passwordTab = "//*[@id='input_password']";
    static String confirmLogin= "//button[contains(@class, 'btn_medium')]";

    //Applicable only if you have Steam Guard enabled
    static String authCode = "//*[@id='authcode']";
    static String submitCode = "//*[@id='auth_buttonset_entercode']//div[@class='auth_button leftbtn']";
    static String proceedToSteam = "//*[@id='success_continue_btn']";

    //Applicable only if you have Family View enabled
    static String disableFamilyView = "//span[contains(@onclick, 'UnlockFamilyView')]";
    static String familyViewPINTab = "//input[@type='password']";
    static String confirmFamilyViewDisable = "//button[@id='submit_btn']";
    static String enableFamilyView = "//span[contains(@onclick, 'LockFamilyView(false)')]";
    static String confirmFamilyViewEnable = "//div[contains(@class, 'btn_green_white_innerfade')]";

    static String userDropdown = "//span[contains(@id, 'account_pulldown')]";
    static String viewProfile = "//a[contains(@class, 'popup') and ./text()='View profile']";
    static String editProfile = "//a[contains(@class, 'btn_medium') and //text()='Edit Profile']";
    static String chooseAvatar = "//input[@type='file']";
    static String uploadButton = "//button[contains(@id, 'upload_button')]";
    static String saveAvatar = "//span[text()='Save Changes']";
    static String confirmSave = "//div[@class='saved_changes_msg']";
    static String logoutUser = "//a[contains(@class, 'popup') and ./text()='Logout']";

}
