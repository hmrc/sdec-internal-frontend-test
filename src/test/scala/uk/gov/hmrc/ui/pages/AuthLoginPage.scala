/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.{By, JavascriptExecutor, WebDriver, WebElement}
import org.openqa.selenium.support.ui.WebDriverWait
import uk.gov.hmrc.ui.conf.TestConfiguration
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import uk.gov.hmrc.ui.driver.BrowserDriver

import java.time.Duration

object AuthLoginPage extends BrowserDriver with BasePage {

  val url: String             = TestConfiguration.url("sdec-internal-frontend")
  val pidName: By             = By.id("pid")
  val givenName: By           = By.id("usersGivenName")
  val surName: By             = By.id("usersSurname")
  val emailAddress: By        = By.id("emailAddress")
  val clickStatusSuccess: By  = By.xpath("//input[@id='success'][@name='status']")
  val clickSignatureValid: By = By.xpath("//input[@id='valid'][@name='signature']")
  val rolesTextarea: By       = By.xpath("//textarea[@id='roles'][@name='roles']")
  val submitButtonClick: By   = By.xpath("//button[@type='submit' and @id='continue-button']")

  private val wait = new WebDriverWait(driver, Duration.ofSeconds(10))

  def getEnterPidNameInput: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(pidName))

  def getEnterGivenNameInput: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(givenName))

  def getEnterSurNameInput: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(surName))

  def getEnterEmailAddressInput: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddress))

  def navigateToAuthPage(): Unit =
    navigateTo(url)

  def enterPIDValue(value: String): Unit = {
    val input = getEnterPidNameInput
    input.clear()
    input.sendKeys(value)
  }

  def enterGivenNameValue(value: String): Unit = {
    val input = getEnterGivenNameInput
    input.clear()
    input.sendKeys(value)
  }

  def enterSurNameValue(value: String): Unit = {
    val input = getEnterSurNameInput
    input.clear()
    input.sendKeys(value)
  }

  def enterEmailAddressValue(value: String): Unit = {
    val input = getEnterEmailAddressInput
    input.clear()
    input.sendKeys(value)
  }

  def selectConfirmAndSendButton(): Unit = {
    val continueButton = wait.until(ExpectedConditions.elementToBeClickable(submitButtonClick))

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", continueButton)

    wait.until(ExpectedConditions.elementToBeClickable(submitButtonClick))

    jsExecutor.executeScript("arguments[0].click();", continueButton)
  }

  def getClickStatusSuccessInput: Boolean = {
    val radioElement = wait.until(
      ExpectedConditions.visibilityOfElementLocated(clickStatusSuccess)
    )

    val radioLocation = radioElement.getLocation
    val radioSize     = radioElement.getSize

    radioElement.isDisplayed &&
    radioSize.getWidth > 0 &&
    radioSize.getHeight > 0 &&
    radioLocation.getY > 0
  }

  def selectStatusSuccess(): Unit = {
    val radioElement = wait.until(
      ExpectedConditions.presenceOfElementLocated(clickStatusSuccess)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", radioElement)

    try
      radioElement.click()
    catch {
      case _: Exception =>
        jsExecutor.executeScript("arguments[0].click();", radioElement)
    }
  }

  def getClickSignatureValidInput: Boolean = {
    val radioElement = wait.until(
      ExpectedConditions.visibilityOfElementLocated(clickSignatureValid)
    )

    val radioLocation = radioElement.getLocation
    val radioSize     = radioElement.getSize

    radioElement.isDisplayed &&
    radioSize.getWidth > 0 &&
    radioSize.getHeight > 0 &&
    radioLocation.getY > 0
  }

  def selectSignatureValid(): Unit = {
    val radioElement = wait.until(
      ExpectedConditions.presenceOfElementLocated(clickSignatureValid)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", radioElement)

    try
      radioElement.click()
    catch {
      case _: Exception =>
        jsExecutor.executeScript("arguments[0].click();", radioElement)
    }
  }

  def enterRolesText(text: String): Unit = {
    val textareaElement = wait.until(
      ExpectedConditions.presenceOfElementLocated(rolesTextarea)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", textareaElement)

    try {
      textareaElement.clear()
      textareaElement.sendKeys(text)
    } catch {
      case _: Exception =>
        jsExecutor.executeScript("arguments[0].value='';", textareaElement)
        jsExecutor.executeScript(s"arguments[0].value='$text';", textareaElement)
    }

  }
}
