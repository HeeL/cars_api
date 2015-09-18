package com.carlist

import akka.actor.Actor
import com.carlist.apis._

class CarActor extends Actor with CarApi {

  def actorRefFactory = context

  def receive = runRoute(carRoute)

}
