package utils

import org.jsoup.nodes.{Document, Element}
import org.scalamock.scalatest.MockFactory
import org.scalatest.{Matchers, WordSpecLike}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.inject.Injector
import play.api.mvc.{AnyContentAsEmpty, ControllerComponents}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.Duration

trait BaseSpec extends WordSpecLike with Matchers with MockFactory with GuiceOneAppPerSuite {

  val request: FakeRequest[AnyContentAsEmpty.type] = FakeRequest()
  val injector: Injector = app.injector

  implicit val cc: ControllerComponents = Helpers.stubControllerComponents()
  implicit val ec: ExecutionContext = ExecutionContext.global
  implicit val defaultTimeout: Duration = Duration(5, "seconds")

  def await[A](future: Future[A])(implicit timeout: Duration): A = Await.result(future, timeout)

  def element(cssSelector: String)(implicit document: Document): Element = {
    val elements = document.select(cssSelector)

    if(elements.size == 0) {
      fail(s"No element exists with the selector '$cssSelector'")
    }

    document.select(cssSelector).first()
  }

  def elementText(cssSelector: String)(implicit document: Document): String = {
    element(cssSelector).text()
  }
}
