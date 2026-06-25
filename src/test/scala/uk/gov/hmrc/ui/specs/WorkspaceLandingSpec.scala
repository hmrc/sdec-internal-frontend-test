package uk.gov.hmrc.ui.specs

import uk.gov.hmrc.ui.driver.BrowserDriver
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.ui.pages.WorkspaceLandingPage

class WorkspaceLandingSpec extends AnyFeatureSpec with GivenWhenThen with BrowserDriver with Matchers with BaseSpec {

  Feature("Workspace landing page") {

    info("As an internal user")
    info("I want to view the workspace landing page with my team information and guidance")
    info("So that I can understand the workspace context and available actions")

    Scenario("AC1 - Display Team Name") {

      Given("the workspace landing page is displayed")

      val page = new WorkspaceLandingPage(driver)

      When("the page loads")
      val heading = page.getHeadingText

      Then("the system must display the internal user's team name as the page heading")
      heading should (be("Child Benefit Service") or be("Your Team"))
    }

    Scenario("AC2 - Display Introductory Text") {

      Given("the workspace landing page is displayed")
      val page = new WorkspaceLandingPage(driver)

      When("the user views the page")
      val introText = page.getIntroductoryText

      Then("the system must display introductory text as defined in the prototype")
      introText shouldBe "Share Files Securely"

      And("the text must be displayed above the Create Thread button")
      page.isCreateThreadButtonDisplayed shouldBe true
    }

    Scenario("AC3 - Display Create Thread Button") {

      Given("the workspace landing page is displayed")
      // goTo("/workspace")
      val page      = new WorkspaceLandingPage(driver)
      val beforeUrl = page.currentUrl

      When("the user views the page")
      val buttonDisplayed = page.isCreateThreadButtonDisplayed
      val buttonEnabled   = page.isCreateThreadButtonEnabled
      val buttonText      = page.getCreateThreadButtonText

      Then("a Create thread button must be displayed")
      buttonDisplayed shouldBe true

      And("the button must be selectable")
      buttonEnabled shouldBe true

      And("the button must follow GOV.UK Design System standards")
      buttonText shouldBe "Create thread"

      When("the user selects the button")
      page.selectCreateThreadButton()

      Then("selecting the button must not trigger any action")
      page.currentUrl shouldBe beforeUrl
    }
  }
}
