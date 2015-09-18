package com.carlist.com.carlist.apis

import com.carlist.apis.CarApi
import com.carlist.models.Car
import org.specs2.mutable.Specification
import spray.http.MediaTypes._
import spray.http.StatusCodes._
import spray.http.{FormData}
import spray.testkit.Specs2RouteTest

import scala.concurrent.duration._
import scala.language.postfixOps

class CarApiSpec extends Specification with Specs2RouteTest with CarApi {
  val duration = new FiniteDuration(1L, MINUTES)
  implicit val routeTestTimeout = RouteTestTimeout(duration)

  def actorRefFactory = system

  val carData = Car.listAll.last

  "CarApi" should {

    "when calling GET v1/cars should return list of cars" in {
      Get("/v1/cars") ~> carRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain("Car Title")
      }
    }

    s"when calling GET v1/cars/${carData.id} should return car" in {
      Get(s"/v1/cars/${carData.id}") ~> carRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain("Car Title")
      }
    }

  }
}
