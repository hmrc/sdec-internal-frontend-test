package uk.gov.hmrc.ui.pages

import org.openqa.selenium.{By, WebDriver, WebElement}
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}

import java.time.Duration

class WorkspaceLandingPage(driver: WebDriver) extends BasePage(driver) {

  private val wait = new WebDriverWait(driver, Duration.ofSeconds(10))

  // Prefer data-testid selectors if you can add them in the UI.
  private val headingLocator: By =
    By.cssSelector("h1")

  private val introTextLocator: By =
    By.xpath("//*[contains(normalize-space(), 'Share Files Securely')]")

  private val createThreadButtonLocator: By =
    By.xpath(
      "//a[normalize-space()='Create thread' or normalize-space()='Create Thread'] | //button[normalize-space()='Create thread' or normalize-space()='Create Thread']"
    )

  def getHeadingText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(headingLocator)).getText.trim

  def getIntroductoryText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(introTextLocator)).getText.trim

  def getCreateThreadButton: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(createThreadButtonLocator))

  def isCreateThreadButtonDisplayed: Boolean =
    getCreateThreadButton.isDisplayed

  def isCreateThreadButtonEnabled: Boolean =
    getCreateThreadButton.isEnabled

  def getCreateThreadButtonText: String =
    getCreateThreadButton.getText.trim

  def selectCreateThreadButton(): Unit =
    getCreateThreadButton.click()

  def isIntroTextDisplayedBeforeButton: Boolean = {
    val introLocation  = wait.until(ExpectedConditions.visibilityOfElementLocated(introTextLocator)).getLocation
    val buttonLocation = getCreateThreadButton.getLocation
    introLocation.getY < buttonLocation.getY
  }
}
