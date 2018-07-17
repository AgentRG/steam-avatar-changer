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
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathSteam.confirmLogin))).click();
        try {
            Thread.sleep(5000);
            if(!(driver.findElements(By.xpath(xPathSteam.authCode)).isEmpty())){
                steamGuardCode.getAuthCode();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void disableFamilyView() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.userDropdown)));
        if(!(driver.findElements(By.xpath(xPathSteam.disableFamilyView)).isEmpty())) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathSteam.disableFamilyView))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.familyViewPINTab)));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathSteam.familyViewPINTab))).click();
            WebElement familyViewPINTab = driver.findElement(By.xpath(xPathSteam.familyViewPINTab));
            familyViewPINTab.sendKeys(userInformation.steamFamilyViewPIN);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathSteam.confirmFamilyViewDisable))).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPathSteam.confirmFamilyViewDisable)));
        }
    }

    private static void editProfile() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.userDropdown)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathSteam.userDropdown))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathSteam.viewProfile))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.editProfile)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathSteam.editProfile))).click();
    }

    private static void uploadAvatar() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.uploadButton)));
        driver.findElement(By.xpath(xPathSteam.chooseAvatar)).sendKeys(changeAvatarModes.avatarName);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathSteam.uploadButton))).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPathSteam.uploadButton)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.uploadButton)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathSteam.saveAvatar))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.confirmSave)));
    }

    private static void logout() {
        if(!(driver.findElements(By.xpath(xPathSteam.enableFamilyView)).isEmpty())) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathSteam.enableFamilyView))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathSteam.confirmFamilyViewEnable))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.disableFamilyView)));
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathSteam.userDropdown))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathSteam.logoutUser))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.loginButton)));
    }
}
