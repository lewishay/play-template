package views

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import utils.BaseSpec
import views.html.ExampleView

class ExampleViewSpec extends BaseSpec {

  val injectedView: ExampleView = injector.instanceOf[ExampleView]

  "The Example page" should {

    lazy implicit val document: Document = Jsoup.parse(injectedView("Lewis").body)

    "have the correct title" in {
      document.title shouldBe "Example title"
    }

    "have the correct heading" in {
      elementText("h1") shouldBe "Hello Lewis!"
    }
  }
}
