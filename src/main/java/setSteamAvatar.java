import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class setSteamAvatar extends Main{

    private static boolean staleElement = true;

    public static void changeAvatar() {
        driver.navigate().to("https://store.steampowered.com/");
        loginScreen();
        enterInformation();
        loginToAccount();
        steamGuardCode.getAuthCode(); //Will trigger only if Steam Guard is enabled.
        disableFamilyView(); //Will trigger only if Family View is enabled. It will be re-enabled at the end with logout().
        editProfile();
        uploadAvatar();
        logout();
        driver.quit();
    }

    private static void loginScreen() {
        while(staleElement) {
            try {
                WebElement loginButton = driver.findElement(By.xpath(xPathSteam.loginButton));
                loginButton.click();
                staleElement = false;
            }
            catch(StaleElementReferenceException e) {
                staleElement = true;
            }
        }
    }

    private static void enterInformation() {
        WebElement email = driver.findElement(By.xpath(xPathSteam.usernameTab));
        WebElement password = driver.findElement(By.xpath(xPathSteam.passwordTab));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.usernameTab)));
        email.sendKeys(userInformation.steamUsername);
        password.sendKeys(userInformation.steamPassword);
    }

    private static void loginToAccount() {
        WebElement login = driver.findElement(By.xpath(xPathSteam.confirmLogin));
        login.click();
    }

    private static void disableFamilyView() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.userDropdown)));
        if(!(driver.findElements(By.xpath(xPathSteam.disableFamilyView)).isEmpty())) {
            WebElement disableFamilyView = driver.findElement(By.xpath(xPathSteam.disableFamilyView));
            disableFamilyView.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.familyViewPINTab)));
            WebElement familyViewPINTab = driver.findElement(By.xpath(xPathSteam.familyViewPINTab));
            WebElement confirmFamilyViewDisable = driver.findElement(By.xpath(xPathSteam.confirmFamilyViewDisable));
            familyViewPINTab.sendKeys(userInformation.steamFamilyViewPIN);
            confirmFamilyViewDisable.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPathSteam.confirmFamilyViewDisable)));
        }
    }

    private static void editProfile() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.userDropdown)));
        WebElement profileDropdown = driver.findElement(By.xpath(xPathSteam.userDropdown));
        profileDropdown.click();
        WebElement viewProfile = driver.findElement(By.xpath(xPathSteam.viewProfile));
        viewProfile.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.editProfile)));
        WebElement editProfile = driver.findElement(By.xpath(xPathSteam.editProfile));
        editProfile.click();
    }

    private static void uploadAvatar() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.uploadButton)));
        WebElement uploadButton = driver.findElement(By.xpath(xPathSteam.uploadButton));
        WebElement saveButton = driver.findElement(By.xpath(xPathSteam.saveAvatar));
        driver.findElement(By.xpath(xPathSteam.chooseAvatar)).sendKeys(changeAvatarModes.avatarName);
        uploadButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPathSteam.uploadButton)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.uploadButton)));
        saveButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.confirmSave)));
    }

    private static void logout() {
        if(!(driver.findElements(By.xpath(xPathSteam.enableFamilyView)).isEmpty())) {
            WebElement enableFamilyView = driver.findElement(By.xpath(xPathSteam.enableFamilyView));
            enableFamilyView.click();
            WebElement confirmEnableFamilyView = driver.findElement(By.xpath(xPathSteam.confirmFamilyViewEnable));
            confirmEnableFamilyView.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.disableFamilyView)));
        }
        WebElement profileDropdown = driver.findElement(By.xpath(xPathSteam.userDropdown));
        WebElement logoutButton = driver.findElement(By.xpath(xPathSteam.logoutUser));
        profileDropdown.click();
        logoutButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.loginButton)));
    }
}
