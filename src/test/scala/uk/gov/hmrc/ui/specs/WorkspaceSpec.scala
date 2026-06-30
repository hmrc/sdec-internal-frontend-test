package uk.gov.hmrc.ui.specs

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.must.Matchers
import uk.gov.hmrc.ui.pages.AuthLoginPage.{driver, login}
import uk.gov.hmrc.ui.pages.WorkspacePage
import uk.gov.hmrc.ui.specs.tags.AcceptanceTests

import java.time.Duration

class WorkspaceSpec extends BaseSpec {
  Feature("Internal User Journey") {

    Scenario("Get Landing Page", AcceptanceTests) {

      Given("User Logins with Credential ID") // This might be the wrong way for internal HMRC staff to login for now
      login()

      When("the dashboard page loads")
      Then("the system must display a dashboard page layout")
      val heading = driver.findElement(By.tagName("h1"))

      And("the system must display a navigation area")

      println(s"URL: ${driver.getCurrentUrl}")
      println(s"Title: ${driver.getTitle}")

      val wait = new WebDriverWait(driver, Duration.ofSeconds(15))

      val workspaceTab = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.id("tab_workspace"))
      )

      val notificationTab = driver.findElement(By.id("tab_notifications"))

      heading.getText         shouldBe "SDEC Internal Dashboard"
      workspaceTab.getText    shouldBe "Workspace"
      notificationTab.getText shouldBe "Notifications"


      val wPage = new WorkspacePage(driver)
      val buttonDisplayed = wPage.isCreateThreadButtonDisplayed
      val buttonEnabled = wPage.isCreateThreadButtonEnabled
      val buttonText = wPage.getThreadButtonText

      Then("""a "Create thread" button must be displayed""")

      buttonDisplayed shouldBe true

      And("the button must be selectable")
      buttonEnabled shouldBe true

      And("the button must follow GOV.UK Design System standards")
      buttonText shouldBe "Create thread"

    }


  }
}
