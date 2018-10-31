package com.apap.tutorial7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@PutMapping(value = "/{carId}")
	private String updateCarSubmit(
			@PathVariable(value = "id") Long carId,
			@RequestParam("brand") String brand,
			@RequestParam("type") String type,
			@RequestParam("price") Long price,
			@RequestParam("amount") int amount,
			@RequestParam("dealerId") Long dealerId){
				CarModel car = carService.getCar(carId);
				if(car.equals(null)) {
					return "Couldn't find your car";
				}
				car.setBrand(brand);
				car.setType(type);
				car.setPrice(price);
				car.setAmount(amount);
				car.setDealer(dealerService.getDealerDetailById(dealerId).get());
				return "car update success";
			}
	
	@PostMapping(value = "/add")
	private CarModel addCarSubmit(@RequestBody CarModel newCar) {
		carService.addCar(newCar);
		//newCar.setDealer(dealer);
		return newCar;
	}
	
	@GetMapping(value = "/{carId}")
	private CarModel viewCar(@PathVariable ("carId") Long carId, Model model) {
		return carService.getCar(carId);
	}
	
	@GetMapping()
	private List<CarModel> viewAllCars(Model model){
		return carService.viewAllCars();
	}
	
	@DeleteMapping(value = "/{carId}")
	private String deleteCar(@PathVariable("carId") Long carId, Model model) {
		CarModel car = carService.getCar(carId);
		carService.deleteCar(car);
		return "Car has been deleted";
	}
}

