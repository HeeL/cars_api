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

  val all_cars = Car.listAll()
  val car1 = all_cars.head
  val car2 = all_cars.last

  "CarApi" should {

    "when calling GET v1/cars should return list of cars" in {
      Get("/v1/cars") ~> carRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain(car2.title)
      }
    }

    s"when calling GET v1/cars/${car2.id} should return car" in {
      Get(s"/v1/cars/${car2.id}") ~> carRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain(car2.title)
      }
    }

    "when calling POST v1/cars should return new created car instance" in {
      var carFormData = Seq(
        "title"   -> "New Car Title",
        "fuel"    -> "1",
        "price"   -> "5500",
        "is_new"  -> "true"
      )

      Post("/v1/cars", FormData(carFormData)) ~> carRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain("New Car Title")
        responseAs[String] must contain("5500")
      }
    }

    "when calling POST v1/cars with mileage should save the value" in {
      var carFormData = Seq(
        "title"   -> "New Car Title",
        "fuel"    -> "1",
        "price"   -> "5500",
        "is_new"  -> "false",
        "mileage" -> "200000"
      )

      Post("/v1/cars", FormData(carFormData)) ~> carRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain("200000")
      }
    }

    s"when calling DELETE v1/cars/${car1.id} should delete exisisting car instance" in {
      Delete(s"/v1/cars/${car1.id}") ~> carRoute ~> check {
        status should beEqualTo(OK)
      }
    }

  }
}
