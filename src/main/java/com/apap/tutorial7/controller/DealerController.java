package com.apap.tutorial7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.rest.DealerDetail;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.DealerService;

@RestController
@RequestMapping("/dealer")
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
	
	@PostMapping(value = "/add")
	private DealerModel addDealerSubmit(@RequestBody DealerModel dealer) {
		dealerService.addDealer(dealer);
		return dealer;
	}
	
	@GetMapping(value = "/{dealerId}")
	private DealerModel viewDealer(@PathVariable ("dealerId") Long dealerId, Model model) {
		return dealerService.getDealerDetailById(dealerId).get();
	}
	
	@DeleteMapping(value = "/delete")
	private String deleteDealer(@RequestParam("dealerId") Long id, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(id).get();
		dealerService.deleteDealer(dealer);
		return "Success";
	}
	
	@PutMapping(value = "/{id}")
	private String updateDealerSubmit(
			@PathVariable(value = "id") Long id,
			@RequestParam("alamat") String alamat,
			@RequestParam("telp") String telp){
				DealerModel dealer = (DealerModel) dealerService.getDealerDetailById(id).get();
				if(dealer.equals(null)) {
					return "Couldn't find your dealer";
				}
				dealer.setAlamat(alamat);
				dealer.setNoTelp(telp);
				dealerService.updateDealer(id, dealer);
				return "update success";
			}
	
	@GetMapping()
	private List<DealerModel> viewAllDealers(Model model){
		return dealerService.viewAllDealers();
	}
	
	@GetMapping(value = "/status/{dealerId}")
	private String getStatus(@PathVariable ("dealerId") Long dealerId) throws Exception{
		String path = Setting.dealerUrl + "/dealer?id=" + dealerId;
		return restTemplate.getForEntity(path, String.class).getBody();
	}
	
	@GetMapping(value = "/full/{dealerId}")
	private DealerDetail postStatus(@PathVariable ("dealerId") Long dealerId) throws Exception{
		String path = Setting.dealerUrl + "/dealer";
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		DealerDetail detail = restTemplate.postForObject(path,dealer, DealerDetail.class);
		return detail;
	}
} 
