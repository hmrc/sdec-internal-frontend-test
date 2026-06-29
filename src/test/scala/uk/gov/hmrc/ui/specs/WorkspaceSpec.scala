package uk.gov.hmrc.ui.specs

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.AuthLoginPage.{driver, login}
import uk.gov.hmrc.ui.specs.tags.AcceptanceTests

import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.verbs.ShouldVerb
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.WorkspaceLandingPage

class WorkspaceSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("Workspace landing page") {

    Scenario("AC1 - Dashboard Page") {
      Given("the internal user accesses the SDEC service")
      WorkspaceLandingPage.navigateWSPage()

      When("the dashboard page loads")
      Then("the system must display a dashboard page layout")
      WorkspaceLandingPage.isPageDisplayed shouldBe true

      And("the system must display a navigation area")
      WorkspaceLandingPage.isWorkspaceTabDisplayed     shouldBe true
      WorkspaceLandingPage.isNotificationsTabDisplayed shouldBe true
    }

    Scenario("AC2 - Display Navigation Tabs") {
      Given("the dashboard page is displayed")
      WorkspaceLandingPage.navigateWSPage()

      When("the user views the navigation area")
      Then("the following tabs must be displayed")
      WorkspaceLandingPage.isWorkspaceTabDisplayed     shouldBe true
      WorkspaceLandingPage.isNotificationsTabDisplayed shouldBe true

      And("the Workspace tab must be selected by default")
      WorkspaceLandingPage.activeNavigationTabText shouldBe "Workspace"
    }

    Scenario("AC3 - Navigation Between Tabs") {
      Given("the navigation tabs are displayed")
      WorkspaceLandingPage.navigateWSPage()

      When("the user views the selected tab")
      Then("the selected tab must be visually indicated as active")
      WorkspaceLandingPage.activeNavigationTabText shouldBe "Workspace"

      And("the user must remain on the selected page or placeholder view")
      WorkspaceLandingPage.isPageDisplayed shouldBe true
    }

    Scenario("AC4 - Display Welcome Message") {
      Given("the dashboard page is displayed")
      WorkspaceLandingPage.navigateWSPage()

      When("the page loads")
      Then("the system must display welcome text as defined in the approved prototype")
      WorkspaceLandingPage.welcomeMessageText shouldBe "Welcome back: Dyfrig Rees"

      And("the internal user's name must be dynamically populated")
      WorkspaceLandingPage.welcomeMessageText should include("Dyfrig Rees")
    }

  }
}
