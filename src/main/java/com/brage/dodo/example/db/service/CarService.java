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
package com.brage.dodo.example.db.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.brage.dodo.example.db.dto.CarDTO;
import com.brage.dodo.example.db.mapper.CarMapper;
import com.brage.dodo.example.db.models.Car;
import com.brage.dodo.example.db.models.Car_;
import com.brage.dodo.jpa.AbstractService;
import com.brage.dodo.jpa.mapper.AbstractModelMapper;

/**
 * @author Dorin Brage
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CarService extends AbstractService<Car, CarDTO> {

	@Inject
	private CarMapper mapper;

	private static final Logger LOG = Logger.getLogger(CarService.class.getName());

	@Override
	public AbstractModelMapper getMapper() {
		return mapper;
	}

	public Car getByLicensePlate(String licensePlate) {
		initializePredicates();
		 addEqualsPredicate(Car_.licensePlate, licensePlate);
		return getSingleResult(false);
	}

}
