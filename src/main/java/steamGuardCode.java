import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;

public class steamGuardCode extends Main {

    private static String steamGuardCode;

    public static void getAuthCode() {
            openNewTab();
            gmailURL();
            loginToGmail();
            openSteamMailAndGetCode();
            logoutFromGmail();
            closeTab();
            typeInAuthCode();
    }

    public static boolean isAlertPresent() {
        try {
            Thread.sleep(500);
            driver.switchTo().alert();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private static void openNewTab() {
        ((JavascriptExecutor)driver).executeScript("window.open('about:blank', '-blank')");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    private static void gmailURL() {
        driver.get(xPathGmail.gmailURL);
    }

    private static void loginToGmail() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathGmail.userEmail)));
        WebElement userEmail = driver.findElement(By.xpath(xPathGmail.userEmail));
        userEmail.sendKeys(userInformation.emailUsername);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathGmail.usernameNext))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathGmail.userPassword)));
        WebElement userPassword = driver.findElement(By.xpath(xPathGmail.userPassword));
        userPassword.sendKeys(userInformation.emailPassword);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathGmail.usernameNext))).click();
    }

    private static void openSteamMailAndGetCode() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathGmail.searchMail)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathGmail.authenticationMail)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathGmail.authenticationMail))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathGmail.authenticationCode)));
        steamGuardCode = driver.findElement(By.xpath(xPathGmail.authenticationCode)).getText();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathGmail.inbox))).click();
    }

    private static void logoutFromGmail() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathGmail.signOutOptions))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathGmail.signOut)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathGmail.signOut))).click();
        if(isAlertPresent()) {
            driver.switchTo().alert();
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
        }
    }

    private static void closeTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    private static void typeInAuthCode() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.authCode)));
        WebElement authCode = driver.findElement(By.xpath(xPathSteam.authCode));
        authCode.sendKeys(steamGuardCode);
        WebElement submitCode = driver.findElement(By.xpath(xPathSteam.submitCode));
        submitCode.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathSteam.proceedToSteam)));
        WebElement proceedToSteam = driver.findElement(By.xpath(xPathSteam.proceedToSteam));
        proceedToSteam.click();
    }

}
