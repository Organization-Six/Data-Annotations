package JavaTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.windows.WindowsDriver;

public class NewTest {
  private static final String JAVA_UI_APPID = "D:\\QQmusic\\QQmusic.exe";
  private static WindowsDriver driver = null;

  @BeforeClass
  public static void setup() throws IOException {
  	try {
  		DesiredCapabilities capabilities = new DesiredCapabilities();
  		capabilities.setCapability("app", JAVA_UI_APPID);
  		capabilities.setCapability("platformName", "Windows");
  		driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
  		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
      } catch (MalformedURLException e) {
  		e.printStackTrace();
  	} finally {
  	}
  }
  
  @Test
  public void start() {
	  WebElement element = this.driver.findElementByName("accessiableName_LineEdit");
	  // 模拟鼠标点击，聚焦
	  element.click();
	  // 简单的模拟输入
	  element.sendKeys("test");
	  // 获取输入后的值
	  String content = element.getText();
	  // 与期望值进行比较
	  //Assert.assertEquals("test", content);

  }

}
