package com.carlist.models

import com.carlist.Db

case class Car(title: String)

object Car {

  def listAll = {
    Db.query[Car].fetch()
  }

}
