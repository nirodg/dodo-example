/*******************************************************************************
 * Copyright 2018 Dorin Brage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS 
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *******************************************************************************/
package com.brage.dodo.example.rs.services;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.brage.dodo.jpa.AbstractService;
import com.brage.dodo.jpa.mapper.AbstractModelMapper;
import com.brage.dodo.rs.AbstractRestServiceBean;
import com.brage.dodo.example.db.dto.CarDTO;
import com.brage.dodo.example.db.mapper.CarMapper;
import com.brage.dodo.example.db.models.Car;
import com.brage.dodo.example.db.service.CarService;
import com.brage.dodo.example.rs.api.CarRestService;

/**
 *
 * @author Dorin Brage
 */
@Stateless
@Local(CarRestService.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CarRestServiceBean extends AbstractRestServiceBean<Car, CarDTO> implements CarRestService {

	private static final Logger LOG = Logger.getLogger(CarRestServiceBean.class.getName());

	@EJB
	private CarService service;

	@Inject
	private CarMapper mapper;

	@Override
	public AbstractModelMapper getMapper() {
		return mapper;
	}

	@Override
	public AbstractService getService() {
		return service;
	}

	@Override
	public CarDTO getByLicensePlate(String licensePlate) {
		Car car = service.getByLicensePlate(licensePlate);
		return mapper.find(car);
	}
}
