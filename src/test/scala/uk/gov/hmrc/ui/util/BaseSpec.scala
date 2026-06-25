package uk.gov.hmrc.ui.util

import org.scalatest.BeforeAndAfterAll
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.ui.driver.BrowserDriver

trait BaseSpec extends AnyFeatureSpec with GivenWhenThen with Matchers with BrowserDriver with BeforeAndAfterAll {

  def goTo(path: String): Unit =
    driver.get(baseUrl + path)

  override def afterAll(): Unit = {
    quitDriver()
    super.afterAll()
  }
}
