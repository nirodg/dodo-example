/*******************************************************************************
 * Copyright 2018 Dorin Brage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *******************************************************************************/
package com.brage.dodo.example.db.service;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import com.brage.dodo.example.db.models.Car;
import com.brage.dodo.example.db.models.Car_;
import ro.brage.dodo.jpa.AbstractService;
import ro.brage.dodo.jpa.Finder;
import ro.brage.dodo.jpa.enums.OrderBy;

/**
 * @author Dorin Brage
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CarService extends AbstractService<Car> {

  public Car getByLicensePlate(String licensePlate) throws Exception {
    return new Finder<>(this)
        .equalTo(Car_.licensePlate, licensePlate)
        .orderBy(Car_.year, OrderBy.ASC)
        .findItem();
  }

  public List<Car> getByMakeAndModel(String make, String model) throws Exception {
    return new Finder<>(this)
        .equalTo(Car_.make, make)
        .equalTo(Car_.model, model)
        .findItems();
  }

  public List<Car> getByModelAndFromYearToCurrent(String model, Date fromYear) throws Exception {
    return new Finder<>(this)
        .equalTo(Car_.model, model)
        .between(Car_.year, fromYear, new Date())
        .orderBy(Car_.year, OrderBy.ASC)
        .findItems();
  }

  public List<Car> filterByYears(Date from, Date to) throws Exception {
    return new Finder<>(this)
        .between(Car_.year, from, to)
        .orderBy(Car_.year, OrderBy.ASC)
        .maxItems(5)
        .findItems();
  }

  public void disableCar(String id) {
    String updateQuery = "UPDATE Car c SET c.enabled=0 where c.id:id";
    getEntityManager().createQuery(updateQuery).setParameter("id", id).executeUpdate();
  }

  public Long getTotalEntities() {
    return getCount();
  }
}
