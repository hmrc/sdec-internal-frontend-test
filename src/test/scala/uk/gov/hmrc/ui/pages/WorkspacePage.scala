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

import org.openqa.selenium.{By, WebDriver, WebElement}
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import uk.gov.hmrc.ui.driver.BrowserDriver

import java.time.Duration

object WorkspacePage extends BrowserDriver with BasePage {

  val workspacePageHeading: By      = By.xpath("//*[@id=\"workspace\"]/h2")
  val workspaceLinkClick: By        = By.xpath("//*[@id=\"tab_workspace\"]")
  val createThreadButtonLocator: By = By.cssSelector("#workspace > div > div > button")

  private val wait = new WebDriverWait(driver, Duration.ofSeconds(10))

  def getWorkspaceHeadingText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(workspacePageHeading)).getText.trim

  def getWorkspaceLinkClick: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(workspaceLinkClick))

  def getCreateThreadButton: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(createThreadButtonLocator))

  def getThreadButtonText: String =
    getCreateThreadButton.getText.trim

  def selectCreateThreadButton(): Unit =
    getCreateThreadButton.click()

}
