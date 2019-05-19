package controllers

import play.api.test.Helpers._
import utils.BaseSpec
import views.html.ExampleView

class ExampleControllerSpec extends BaseSpec {

  val controller = new ExampleController(injector.instanceOf[ExampleView])

  "The .show() action" should {

    "return 200" in {
      val result = controller.show("Lewis")(request)
      status(result) shouldBe 200
    }
  }
}
