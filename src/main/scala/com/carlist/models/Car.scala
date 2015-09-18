package com.carlist.models

import com.carlist.Db

case class Car(title: String)

object Car {

  def findById(id: Int) = Db.query[Car].whereEqual("id", id).fetchOne()

  def listAll(sort: Option[Int] = Some(0)) = {
    val sort_by = Map(1 -> "title").getOrElse(sort.getOrElse(0), "id")
    Db.query[Car].fetch()
  }

  def create(title: String) = {
    Db.save(Car(title))
  }

  def update(id: Int, title: String) = {
    findById(id).map(c => c.copy(title = title)).map(Db.save)
  }

  def delete(id: Int) = findById(id).map(c => Db.delete(c))

}
