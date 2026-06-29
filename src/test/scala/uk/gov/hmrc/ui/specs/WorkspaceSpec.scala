package uk.gov.hmrc.ui.specs

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.AuthLoginPage.{driver, login}
import uk.gov.hmrc.ui.pages.WorkspaceLandingPage
import uk.gov.hmrc.ui.specs.tags.AcceptanceTests
class WorkspaceSpec extends BaseSpec {
  Feature("Internal User Journey") {

    Scenario("Get Landing Page", AcceptanceTests) {

      Given("User Logins with Credential ID") // This might be the wrong way for internal HMRC staff to login for now
      login()
      When("the dashboard page loads")
      Then("the system must display a dashboard page layout")
      WorkspaceLandingPage.isPageDisplayed shouldBe true

      And("the system must display a navigation area")
      WorkspaceLandingPage.isWorkspaceTabDisplayed     shouldBe true
      WorkspaceLandingPage.isNotificationsTabDisplayed shouldBe true
      // val heading = driver.findElement(By.tagName("h1"))
      // heading.getText shouldBe "SDEC Internal Dashboard"

    }
  }
}
