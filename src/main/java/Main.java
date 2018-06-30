import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

    /**
     * This project uses Selenium to upload avatars from a specified folder to your Steam account.
     *
     * Two modes:
     * 1. Randomly pick a picture from the folder and upload it.
     * 2. Incrementally upload a picture from the folder.
     *
     * Uncomment whichever mode you want to use and fill in the required information in userInformation.
     */

    static WebDriver driver = new ChromeDriver();
    static WebDriverWait wait = new WebDriverWait(driver, 30);

    public static void main(String[] args) {
        try {
            //changeAvatarModes.randomChoice();
            //changeAvatarModes.incrementalChoice();
        }
        catch (Exception e){
            driver.quit();
        }
    }
}
