package uk.gov.hmrc.ui.driver

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}

trait BrowserDriver {

  lazy val baseUrl: String =
    sys.env.getOrElse("BASE_URL", "http://localhost:4000")

  lazy val driver: WebDriver = {
    val options = new ChromeOptions()
    options.addArguments("--headless=new")
    options.addArguments("--no-sandbox")
    options.addArguments("--disable-dev-shm-usage")
    options.addArguments("--window-size=1920,1080")
    new ChromeDriver(options)
  }

  def quitDriver(): Unit =
    if (driver != null) driver.quit()
}
