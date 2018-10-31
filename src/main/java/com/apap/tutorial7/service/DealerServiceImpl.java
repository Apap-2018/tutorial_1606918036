package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.DealerDb;

@Service
@Transactional
public class DealerServiceImpl implements DealerService{
	@Autowired
	private DealerDb dealerDb;
	
	@Override
	public Optional<DealerModel> getDealerDetailById(Long id){
		return dealerDb.findById(id);
	}
	
	@Override
	public void addDealer(DealerModel dealer) {
		dealerDb.save(dealer);
	}
	
	@Override
	public List<DealerModel> viewAllDealers() {
		return dealerDb.findAll();
	}

	@Override
	public void updateDealer(Long id, DealerModel newDealer) {
		DealerModel dealer = dealerDb.getOne(id);
		dealer.setAlamat(newDealer.getAlamat());
		dealer.setNoTelp(newDealer.getNoTelp());
		dealerDb.save(dealer);
		
	}
	
	@Override
	public void deleteDealer(DealerModel dealer) {
		dealerDb.delete(dealer);
	}
}
