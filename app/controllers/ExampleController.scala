package controllers

import javax.inject.Inject

import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import views.html.ExampleView

class ExampleController @Inject()(exampleView: ExampleView)
                                 (implicit val cc: ControllerComponents) extends AbstractController(cc) {

  def show(name: String): Action[AnyContent] = Action { implicit request =>
    Ok(exampleView(name))
  }
}
