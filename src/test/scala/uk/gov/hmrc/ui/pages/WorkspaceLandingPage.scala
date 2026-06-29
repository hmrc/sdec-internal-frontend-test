/*
 * Copyright 2023 HM Revenue & Customs
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

import org.openqa.selenium.{By, WebElement}

import uk.gov.hmrc.ui.driver.BrowserDriver

import scala.jdk.CollectionConverters.*

object WorkspaceLandingPage extends BasePage with BrowserDriver {
  val baseUrl: String = "http://localhost:4000"

  private val welcomeMessageLocator: By =
    By.xpath("//li[contains(@class,'govuk-service-navigation__item') and contains(normalize-space(),'Welcome back')]")

  private val workspaceTabLocator: By =
    By.xpath("//li[contains(@class,'govuk-service-navigation__item--active')]//strong[normalize-space()='Workspace']")

  private val notificationsTabLocator: By =
    By.xpath("//a[contains(@class,'govuk-service-navigation__link') and contains(normalize-space(),'Notifications')]")

  private val serviceNavigationLocator: By =
    By.cssSelector(".govuk-service-navigation")

  private val captionLocator: By =
    By.cssSelector("span.govuk-caption-l")

  private val headingLocator: By =
    By.cssSelector("h1.govuk-heading-l")

  def navigateWSPage(): Unit =
    driver.navigate().to(s"$baseUrl/workspace")
  // =========================
  // Element getters
  // =========================

  def isPageDisplayed: Boolean =
    driver.findElements(serviceNavigationLocator).asScala.nonEmpty &&
      driver.findElements(captionLocator).asScala.nonEmpty &&
      driver.findElements(headingLocator).asScala.nonEmpty

  def welcomeMessageText: String =
    driver.findElement(welcomeMessageLocator).getText.trim

  def isWorkspaceTabDisplayed: Boolean =
    driver.findElements(workspaceTabLocator).asScala.nonEmpty

  def isNotificationsTabDisplayed: Boolean =
    driver.findElements(notificationsTabLocator).asScala.nonEmpty

  def activeNavigationTabText: String =
    driver.findElement(workspaceTabLocator).getText.trim

}
