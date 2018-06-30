import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;

public class steamGuardCode extends Main {

    private static String steamGuardCode;

    public static void getAuthCode() {
        if(!(driver.findElements(By.xpath(xPathSteam.authCode)).isEmpty())){
            openNewTab();
            gmailURL();
            loginToGmail();
            openSteamMailAndGetCode();
            logoutFromGmail();
            closeTab();
            typeInAuthCode();
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
        WebElement usernameNext = driver.findElement(By.xpath(xPathGmail.usernameNext));
        userEmail.sendKeys(userInformation.emailUsername);
        usernameNext.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathGmail.userPassword)));
        WebElement userPassword = driver.findElement(By.xpath(xPathGmail.userPassword));
        userPassword.sendKeys(userInformation.emailPassword);
        WebElement passwordNext = driver.findElement(By.xpath(xPathGmail.passwordNext));
        passwordNext.click();
    }

    private static void openSteamMailAndGetCode() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathGmail.signOutOptions)));
        WebElement steamGuardEmail = driver.findElement(By.xpath(xPathGmail.authenticationMail));
        steamGuardEmail.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathGmail.authenticationCode)));
        steamGuardCode = driver.findElement(By.xpath(xPathGmail.authenticationCode)).getText();
    }

    private static void logoutFromGmail() {
        WebElement signOutOptions = driver.findElement(By.xpath(xPathGmail.signOutOptions));
        signOutOptions.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathGmail.signOut)));
        WebElement signOut = driver.findElement(By.xpath(xPathGmail.signOut));
        signOut.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathGmail.userPassword)));
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
