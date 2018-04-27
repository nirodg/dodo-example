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
package com.brage.dodo.example.rs.services;

import java.util.Date;
import java.util.Set;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import com.brage.dodo.example.db.dto.CarDTO;
import com.brage.dodo.example.db.mapper.CarMapper;
import com.brage.dodo.example.db.models.Car;
import com.brage.dodo.example.db.service.CarService;
import com.brage.dodo.example.rs.api.CarRestService;
import com.brage.dodo.rs.AbstractRestServiceBean;

/**
 *
 * @author Dorin Brage
 */
@Stateless
@Local(CarRestService.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CarRestServiceBean extends AbstractRestServiceBean<Car, CarDTO, CarService, CarMapper>
    implements CarRestService {

  @Override
  public CarDTO getByLicensePlate(String licensePlate) throws Exception {
    getLogger().info("getByLicensePlate({})", licensePlate);

    Car car = getService().getByLicensePlate(licensePlate);
    return getMapper().find(car);
  }

  @Override
  public Set<CarDTO> filterByYears(Date from, Date to) throws Exception {
    getLogger().info("filterByYears({}, {})", from, to);

    Set<Car> cars = getService().filterByYears(from, to);
    return getMapper().findDTOs(cars);
  }

  @Override
  public Long getTotalEntities() {
    return getService().getTotalEntities();
  }

}
