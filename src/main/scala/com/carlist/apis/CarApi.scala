package com.carlist.apis

import akka.actor.Props
import akka.pattern.ask
import com.carlist.actors._
import spray.http.MediaTypes._


trait CarApi extends BaseHttpService with ActorHelper {

  val car = actorRefFactory.actorOf(Props[CarActor], "car")

  val carRoute = pathPrefix("v1") {
    path("cars") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            (car ? "list").mapTo[String]
          }
        }
      }
  }~
  pathPrefix("cars" / IntNumber) { id =>
    get {
      respondWithMediaType(`application/json`) {
        complete {
          (car ? ("show", id)).mapTo[String]
        }
      }
    }

}
