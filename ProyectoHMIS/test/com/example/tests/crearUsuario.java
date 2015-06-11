package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

public class crearUsuario {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
	/*driver = new HtmlUnitDriver();
	// turn off htmlunit warnings
	java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
	java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);*/  
	
    baseUrl = "http://ualhmis15.cloudapp.net/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCrearUsuario() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Sign up")).click();
    driver.findElement(By.id("user_username")).clear();
    driver.findElement(By.id("user_username")).sendKeys("usuarioNCC");
    driver.findElement(By.id("user_email")).clear();
    driver.findElement(By.id("user_email")).sendKeys("email@email.es");
    driver.findElement(By.id("user_password")).clear();
    driver.findElement(By.id("user_password")).sendKeys("1234");
    driver.findElement(By.id("user_password_confirmation")).clear();
    driver.findElement(By.id("user_password_confirmation")).sendKeys("1234");
    driver.findElement(By.name("commit")).click();
    assertEquals("A message with a confirmation link has been sent to your email address. Please follow the link to activate your account.", driver.findElement(By.id("flash_notice")).getText());
    driver.findElement(By.linkText("Sign in")).click();
    driver.findElement(By.id("user_username")).clear();
    driver.findElement(By.id("user_username")).sendKeys("usuarioNCC");
    driver.findElement(By.id("user_password")).clear();
    driver.findElement(By.id("user_password")).sendKeys("1234");
    driver.findElement(By.name("commit")).click();
    assertEquals("You have to confirm your email address before continuing.", driver.findElement(By.id("flash_alert")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
