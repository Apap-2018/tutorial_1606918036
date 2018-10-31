package com.apap.tutorial7.service;
import java.util.List;

import com.apap.tutorial7.model.CarModel;

public interface CarService {
	void addCar(CarModel car);
	void deleteCar(CarModel car);
	CarModel getCar(Long id);
	public void deleteById(Long id);
	void updateCar(Long id,CarModel car);
	List<CarModel> getListCarOrderByPriceAsc(Long dealerId);
	List<CarModel> viewAllCars();
}
