package ua.sh1chiro.TelegramBot.api;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ua.sh1chiro.TelegramBot.model.Post;

import java.io.IOException;
import java.util.List;

/**
 * Created by Sh1chiro on 05.02.2024.
 *
 * @author Sh1chiro
 */

public class InstagramAPI {
    public static void post(Post post, String nickname, String password){
        System.setProperty("webdriver.chrome.driver", "D:\\Documents\\Programming\\Projects\\FREELANCE\\TelegramBot\\src\\main\\resources\\drive\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Логін до Instagram
        driver.get("https://www.instagram.com/");
        try {
            Thread.sleep(2000); // Почекайте 5 секунд (можливо, потрібно буде налаштувати)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));

        usernameInput.sendKeys(nickname);
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();



        // Очікування входу
        try {
            Thread.sleep(6000); // Почекайте 10 секунд (можливо, потрібно буде налаштувати)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            WebElement notNow = driver.findElement(By.cssSelector("._a9--._ap36._a9_1"));
            notNow.click();
        }catch (Exception e){
            System.out.println("Error in Not now block");
        }
        String xPathString = ".x1i10hfl.xjbqb8w.x1ejq31n.xd10rxx.x1sy0etr.x17r0tee.x972fbf.xcfux6l.x1qhh985.xm0m39n.x9f619.x1ypdohk.xt0psk2.xe8uvvx.xdj266r.x11i5rnm.xat24cr.x1mh8g0r.xexx8yu.x4uap5.x18d9i69.xkhd6sd.x16tdsg8.x1hl2dhg.xggy1nq.x1a2a7pz._a6hd:nth-child(2)";
        List<WebElement> elements = driver.findElements(By.cssSelector("[href='#']"));
        WebElement newPost = elements.get(2);
        System.out.println("Find for click to add new post: "  + elements.size());
        newPost.click();


        WebElement addPost = driver.findElement(By.cssSelector(".x1i10hfl.x1qjc9v5.xjbqb8w.xjqpnuy.xa49m3k.xqeqjp1" +
                ".x2hbi6w.x13fuv20.xu3j5b3.x1q0q8m5.x26u7qi.x972fbf.xcfux6l.x1qhh985.xm0m39n.x9f619.x1ypdohk.xdl72j9" +
                ".x2lah0s.xe8uvvx.xdj266r.x11i5rnm.xat24cr.x1mh8g0r.x2lwn1j.xeuugli.xexx8yu.x4uap5.x18d9i69.xkhd6sd" +
                ".x1n2onr6.x16tdsg8.x1hl2dhg.xggy1nq.x1ja2u2z.x1t137rt.x1q0g3np.x87ps6o.x1lku1pv.x1a2a7pz.x1dm5mii" +
                ".x16mil14.xiojian.x1yutycm.x1lliihq.x193iq5w.xh8yej3"));
        addPost.click();
        try {
            Thread.sleep(5000); // Почекайте 10 секунд (можливо, потрібно буде налаштувати)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement elementBtn = driver.findElement(By.cssSelector("button._acan._acap._acas._aj1-._ap30"));
        WebElement divElementsCover = driver.findElement(By.cssSelector(".x1qjc9v5.x9f619.x78zum5.xdt5ytf.x1iyjqo2.xl56j7k"));
        JavascriptExecutor executorCoverElement = (JavascriptExecutor) driver;
        executorCoverElement.executeScript("arguments[0].style.pointerEvents = 'none';",divElementsCover);
        System.out.println("Btn add video: " + elementBtn.getTagName());

        // Завантаження відео
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
        fileInput.sendKeys(post.getVideo());

        try {
            Thread.sleep(10000); // Почекайте 10 секунд (можливо, потрібно буде налаштувати)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            WebElement okBnt = driver.findElement(By.cssSelector("._acan._acap._acaq._acas._acav._aj1-._ap30"));
            okBnt.click();
        }catch (Exception e){
            System.out.println("Info reals window ok click error");
            WebElement okBnt = driver.findElement(By.cssSelector("._acan._acap._acaq._acas._acav._aj1-._ap30"));
            okBnt.click();
        }
        try {
            Thread.sleep(5000); // Почекайте 10 секунд (можливо, потрібно буде налаштувати)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            WebElement nextStepBtn = driver.findElement(By.cssSelector("[text = 'Далі'"));

            nextStepBtn.click();
        }catch (Exception e){
            System.out.println("Error in first try click first next btn");
            WebElement nextStepBtn = driver.findElement(By.cssSelector(".x1i10hfl.xjqpnuy.xa49m3k.xqeqjp1.x2hbi6w.xdl72j9.x2lah0s.xe8uvvx" +
                    ".xdj266r.x11i5rnm.xat24cr.x1mh8g0r.x2lwn1j.xeuugli.x1hl2dhg.xggy1nq.x1ja2u2z.x1t137rt.x1q0g3np" +
                    ".x1lku1pv.x1a2a7pz.x6s0dn4.xjyslct.x1ejq31n.xd10rxx.x1sy0etr.x17r0tee.x9f619.x1ypdohk" +
                    ".x1f6kntn.xwhw2v2.xl56j7k.x17ydfre.x2b8uid.xlyipyv.x87ps6o.x14atkfc.xcdnw81.x1i0vuye.xjbqb8w" +
                    ".xm3z3ea.x1x8b98j.x131883w.x16mih1h.x972fbf.xcfux6l.x1qhh985.xm0m39n.xt0psk2.xt7dq6l.xexx8yu" +
                    ".x4uap5.x18d9i69.xkhd6sd.x1n2onr6.x1n5bzlp.x173jzuc.x1yc6y37"));
            nextStepBtn.click();
        }finally {
            System.out.println("Fatal error in some elements first next btn");
        }

        try {
            Thread.sleep(5000); // Почекайте 10 секунд (можливо, потрібно буде налаштувати)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try{
            WebElement nextStepBtn2 = driver.findElement(By.cssSelector("[text = 'Далі'"));
            nextStepBtn2.click();
        }catch (Exception e){
            System.out.println("Error in second nextStep btn");
            WebElement nextStepBtn2 = driver.findElement(By.cssSelector(".x1i10hfl.xjqpnuy.xa49m3k.xqeqjp1.x2hbi6w" +
                    ".xdl72j9.x2lah0s.xe8uvvx.xdj266r.x11i5rnm.xat24cr.x1mh8g0r.x2lwn1j.xeuugli.x1hl2dhg.xggy1nq" +
                    ".x1ja2u2z.x1t137rt.x1q0g3np.x1lku1pv.x1a2a7pz.x6s0dn4.xjyslct.x1ejq31n.xd10rxx.x1sy0etr.x17r0tee" +
                    ".x9f619.x1ypdohk.x1f6kntn.xwhw2v2.xl56j7k.x17ydfre.x2b8uid.xlyipyv.x87ps6o.x14atkfc.xcdnw81" +
                    ".x1i0vuye.xjbqb8w.xm3z3ea.x1x8b98j.x131883w.x16mih1h.x972fbf.xcfux6l.x1qhh985.xm0m39n.xt0psk2" +
                    ".xt7dq6l.xexx8yu.x4uap5.x18d9i69.xkhd6sd.x1n2onr6.x1n5bzlp.x173jzuc.x1yc6y37"));
            nextStepBtn2.click();
        }finally {
            System.out.println("Fatal error in some elements next btn 2");
        }
        try {
            Thread.sleep(5000); // Почекайте 10 секунд (можливо, потрібно буде налаштувати)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement headerElement = driver.findElement(By.cssSelector("[role=textbox]"));
        headerElement.sendKeys(post.getTitle());
        try {
            Thread.sleep(2000); // Почекайте 10 секунд (можливо, потрібно буде налаштувати)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            WebElement shareBtn = driver.findElement(By.cssSelector("[text = 'Поширити'"));
            shareBtn.click();
        }catch (Exception e){
            System.out.println("Error in share btn");
            WebElement shareBtn = driver.findElement(By.cssSelector(".x1i10hfl.xjqpnuy.xa49m3k.xqeqjp1.x2hbi6w.xdl72j9" +
                    ".x2lah0s.xe8uvvx.xdj266r.x11i5rnm.xat24cr.x1mh8g0r.x2lwn1j.xeuugli.x1hl2dhg.xggy1nq.x1ja2u2z" +
                    ".x1t137rt.x1q0g3np.x1lku1pv.x1a2a7pz.x6s0dn4.xjyslct.x1ejq31n.xd10rxx.x1sy0etr.x17r0tee.x9f619" +
                    ".x1ypdohk.x1f6kntn.xwhw2v2.xl56j7k.x17ydfre.x2b8uid.xlyipyv.x87ps6o.x14atkfc.xcdnw81.x1i0vuye" +
                    ".xjbqb8w.xm3z3ea.x1x8b98j.x131883w.x16mih1h.x972fbf.xcfux6l.x1qhh985.xm0m39n.xt0psk2.xt7dq6l.xexx8yu" +
                    ".x4uap5.x18d9i69.xkhd6sd.x1n2onr6.x1n5bzlp.x173jzuc.x1yc6y37"));
            shareBtn.click();
        }finally {
            System.out.println("Fatal error in some elements share btn click");
        }
        try {
            Thread.sleep(3000); // Почекайте 10 секунд (можливо, потрібно буде налаштувати)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
        System.out.println("Post successful");
    }
}
