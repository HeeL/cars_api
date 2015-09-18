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
        parameters('sort.?) {
          sort =>
            respondWithMediaType(`application/json`) {
              complete {
                (car ? ("list", sort)).mapTo[String]
              }
            }
        }
      } ~
      post {
        formFields('title.as[String], 'fuel.as[Int], 'price.as[Int], 'is_new.as[Boolean]) {
          (title, fuel, price, is_new) =>
            respondWithMediaType(`application/json`) {
              complete {
                (car ? ("create", title, fuel, price, is_new)).mapTo[String]
              }
            }
        }
      }
    } ~
    pathPrefix("cars" / IntNumber) { id =>
      get {
        respondWithMediaType(`application/json`) {
          complete {
            (car ? ("show", id)).mapTo[String]
          }
        }
      } ~
      put {
        formFields('title.as[String], 'fuel.as[Int], 'price.as[Int], 'is_new.as[Boolean]) {
          (title, fuel, price, is_new) =>
          respondWithMediaType(`application/json`) {
            complete {
              (car ? ("update", id, title, fuel, price, is_new)).mapTo[String]
            }
          }
        }
      } ~
      delete {
        complete {
          (car ? ("delete", id)).mapTo[String]
        }
      }
    }
  }

}
