package com.carlist.actors

import akka.actor.{Actor, ActorLogging}
import com.carlist.JsonUtil
import com.carlist.models.Car

class CarActor extends Actor with ActorLogging {

  def receive = {
    case ("list", sort: Option[Int]) =>
      val cars = Car.listAll(sort)
      val result = JsonUtil.toJson(cars)
      sender ! result

    case ("show", id: Int) =>
      val car = Car.findById(id)
      val result = JsonUtil.toJson(car)
      sender ! result

    case ("create", title: String, fuel: Int, price: Int, is_new: Boolean) =>
      val car = Car.create(title, fuel, price, is_new)
      val result = JsonUtil.toJson(car)
      sender ! result

    case ("update", id: Int, title: String, fuel: Int, price: Int, is_new: Boolean) =>
      val car = Car.update(id, title, fuel, price, is_new)
      val result = JsonUtil.toJson(car)
      sender ! result

    case ("delete", id: Int) =>
      val car = Car.delete(id)
      sender ! ""
  }

}
