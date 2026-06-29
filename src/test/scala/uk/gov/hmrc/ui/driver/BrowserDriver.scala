package uk.gov.hmrc.ui.driver

import com.typesafe.scalalogging.LazyLogging
import org.openqa.selenium.WebDriver
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import uk.gov.hmrc.selenium.webdriver.{Browser, Driver, ScreenshotOnFailure}
import uk.gov.hmrc.ui.specs.BaseSpec

trait BrowserDriver extends LazyLogging {
  def driver: WebDriver = {
    if Option(Driver.instance).isEmpty then {
      val msg = "WebDriver (Driver.instance) is null! Make sure startBrowser() was called before using driver."
      logger.error(msg)
      throw new IllegalStateException(msg)
    }
    Driver.instance
  }
}
