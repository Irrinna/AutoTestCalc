import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class AutoTest {

    private static WiniumDriverService service;
    private static WiniumDriver driver;

    @BeforeTest
    public static void setupEnvironmentWinium(){
        DesktopOptions options = new DesktopOptions();
        options.setApplicationPath("C:\\Users\\Irina\\Desktop\\TestCalc.exe");
        File driverPath = new File("C:\\Users\\Irina\\Desktop\\Winium.Desktop.Driver.exe");
        service = new WiniumDriverService.Builder()
                 .usingDriverExecutable(driverPath)
                 .usingPort(9999)
                 .withVerbose(true)
                 .withSilent(false)
                 .buildDesktopService();
        try {
            service.start();
        } catch (IOException e) {
            System.out.println("Exception while starting WINIUM service");
            e.printStackTrace();
        }
         driver = new WiniumDriver(service, options);
    }

    @AfterTest
    public void winiumDown(){
        service.stop();
    }

    @Test
    public void testSum() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElementById("button1").click();
        driver.findElementById("button19").click();
        driver.findElementById("button6").click();
        driver.findElementById("button20").click();
        String result = driver.findElementById("textBox1").getText();
        driver.findElementByName("Закрыть").click();
        Assert.assertEquals("6", result);
    }

}
