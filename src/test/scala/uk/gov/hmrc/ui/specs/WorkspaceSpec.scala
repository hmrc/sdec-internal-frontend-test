package uk.gov.hmrc.ui.specs

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.AuthLoginPage.{driver, login}
import uk.gov.hmrc.ui.specs.tags.AcceptanceTests

class WorkspaceSpec extends BaseSpec {

  Feature("Internal User Journey") {
    Scenario("Get Landing Page", AcceptanceTests) {
      Given("User Logins with Credential ID") // This might be the wrong way for internal HMRC staff to login for now
      login()

      Then("The Workspace page displays")
      println(driver.getCurrentUrl)
      val heading = driver.findElement(By.tagName("h1"))

      heading.getText shouldBe "SDEC Internal Dashboard"

    }
  }
}
