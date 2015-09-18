package com.carlist.actors

import akka.actor.{Actor, ActorLogging}
import com.carlist.JsonUtil
import com.carlist.models.Car

class CarActor extends Actor with ActorLogging {

  def receive = {
    case ("list") =>
      val cars = Car.listAll
      val result = JsonUtil.toJson(cars)
      sender ! result
  }

}
