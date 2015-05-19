import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpResponse, HttpMethods, HttpRequest}
import akka.http.scaladsl.server.Directives
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.Sink

object BugDemo extends App {
  implicit val actorSystem = ActorSystem()
  implicit val flowMaterializer = ActorFlowMaterializer()

  val http = Http()
  val req = HttpRequest(HttpMethods.GET, "http://localhost:8080")

  for (i <- 1 to 100000) {
    http.singleRequest(req)
  }
}

object TestServer extends App with Directives {
  implicit val system = ActorSystem()
  implicit val flowMaterializer = ActorFlowMaterializer()

  val requestHandler: HttpRequest => HttpResponse = {
    _ => HttpResponse()
  }

  val serverSource = Http().bind("localhost", 8080)

  serverSource.to(Sink.foreach { connection =>
    connection handleWithSyncHandler requestHandler
  }).run()
}

