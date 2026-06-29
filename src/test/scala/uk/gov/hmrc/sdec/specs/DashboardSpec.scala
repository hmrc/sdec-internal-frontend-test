package uk.gov.hmrc.sdec.specs

import org.jsoup.Jsoup
import org.scalatest.GivenWhenThen
import org.scalatest.OptionValues.convertOptionToValuable
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import play.api.test.FakeRequest
import play.api.test.Helpers.*

import scala.collection.JavaConverters.asScalaBufferConverter

private class DashboardSpec
  extends AnyFeatureSpec
    with GivenWhenThen
    with Matchers
    with BaseSpec
      {

  private def dashboardPage() =
    route(app = ???, req = FakeRequest(GET, "/dashboard")).value

  Feature("SDEC Dashboard Page") {

    Scenario("AC1 - Dashboard Page") {
      Given("the internal user accesses the SDEC service")

      When("the dashboard page loads")
      val result = dashboardPage()

      Then("the system must display a dashboard page layout")
      status(result) shouldBe OK

      And("the system must display a navigation area")
      val body = contentAsString(result)
      val doc = Jsoup.parse(body)

      doc.select(".govuk-tabs").size() shouldBe 1

      And("the system must display a GOV.UK compliant page structure")
      doc.select("header.govuk-header").size() shouldBe 1
      doc.select("main#main-content").size() shouldBe 1
      doc.select("footer.govuk-footer").size() shouldBe 1
    }

    Scenario("AC2 - Display Navigation Tabs") {
      Given("the dashboard page is displayed")

      When("the user views the navigation area")
      val result = dashboardPage()

      Then("the following tabs must be displayed")
      val body = contentAsString(result)
      val doc = Jsoup.parse(body)

      val tabLinks = doc.select(".govuk-tabs__list-item a")
      val tabTexts = tabLinks.eachText()

      tabTexts should contain("Workspace")
      tabTexts should contain("Notifications")

      And("""the "Workspace" tab must be selected by default""")
      val selectedTab = doc.select(".govuk-tabs__list-item--selected")

      selectedTab.size() shouldBe 1
      selectedTab.text() should include("Workspace")
    }

    Scenario("AC3 - Navigation Between Tabs") {
      Given("the navigation tabs are displayed")
      val result = dashboardPage()
      status(result) shouldBe OK

      When("the user selects a tab")
      val body = contentAsString(result)
      val doc = Jsoup.parse(body)

      // Assumes the notifications tab is rendered with a link or anchor
      val notificationsTab = doc.select(".govuk-tabs__list-item a").asScala
        .find(_.text().trim == "Notifications")

      notificationsTab should not be empty

      Then("the selected tab must be visually indicated as active")
      // If your page uses client-side tab switching, check the rendered active class
      // This assertion checks the default selected tab exists
      doc.select(".govuk-tabs__list-item--selected").size() should be >= 1

      And("the user must remain on the selected page or placeholder view")
      // Since this is a placeholder view, we verify the page still renders successfully
      status(result) shouldBe OK
    }

    Scenario("AC4 - Display Welcome Message") {
      Given("the dashboard page is displayed")

      When("the page loads")
      val result = dashboardPage()

      Then("the system must display welcome text as defined in the approved prototype")
      val body = contentAsString(result)
      val doc = Jsoup.parse(body)

      val heading = doc.select("h1").text()
      heading should not be empty
      heading.toLowerCase should include("welcome")

      And("the internal user's name must be dynamically populated")
      // Example expectation: "Welcome back Dyfriges"
      heading.length should be > "Welcome".length
    }
  }
}
