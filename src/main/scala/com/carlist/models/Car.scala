package com.carlist.models

import com.carlist.Db

case class Car(title: String, fuel: Int, price: Int, is_new: Boolean, mileage: Option[Int]){
  if (!is_new) {
    require(mileage.size > 0, "The mileage has to be specified if the car is used")
  }
}

object Car {

  def findById(id: Int) = Db.query[Car].whereEqual("id", id).fetchOne()

  def listAll(sort: Option[Int] = Some(0)) = {
    val sort_by = Map(1 -> "title", 2 -> "price", 3 -> "mileage").getOrElse(sort.getOrElse(0), "id")
    Db.query[Car].fetch()
  }

  def create(title: String, fuel: Int, price: Int, is_new: Boolean, mileage: Option[Int]) = {
    Db.save(Car(title, fuel, price, is_new, mileage))
  }

  def update(id: Int, title: String, fuel: Int, price: Int, is_new: Boolean) = {
    findById(id).map{
      c => c.copy(title = title, fuel = fuel, price = price, is_new = is_new)
    }.map(Db.save)
  }

  def delete(id: Int) = findById(id).map(c => Db.delete(c))

}
