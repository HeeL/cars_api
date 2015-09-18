package com.carlist

import com.carlist.models._
import sorm._


object Db extends Instance(
  entities = Set() + Entity[Car](),
  url = "jdbc:postgresql://localhost/car_listing_api",
  user = "heel",
  password = "",
  initMode = InitMode.DropAllCreate
) {

  // seeding
  Car.create("Car Title")
}

