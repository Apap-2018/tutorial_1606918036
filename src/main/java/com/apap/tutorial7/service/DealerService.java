package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.DealerModel;

public interface DealerService {
	Optional<DealerModel> getDealerDetailById(Long id);
	void addDealer(DealerModel dealer);
	List<DealerModel> viewAllDealers();
	void updateDealer(Long id, DealerModel dealer);
	void deleteDealer(DealerModel dealer);
}
