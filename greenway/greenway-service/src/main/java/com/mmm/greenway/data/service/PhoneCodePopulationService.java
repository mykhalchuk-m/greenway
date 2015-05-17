package com.mmm.greenway.data.service;

import com.mmm.greenway.data.repository.PhoneOperatorCodeRepository;
import com.mmm.greenway.entity.PhoneOperatorCode;

public class PhoneCodePopulationService {
	private PhoneOperatorCodeRepository phoneOperatorCodeRepository;

	public PhoneCodePopulationService(PhoneOperatorCodeRepository phoneOperatorCodeRepository) {
		this.phoneOperatorCodeRepository = phoneOperatorCodeRepository;
	}
	
	public void populateDevaultValie() {
		phoneOperatorCodeRepository.save(new PhoneOperatorCode("099"));
		phoneOperatorCodeRepository.save(new PhoneOperatorCode("098"));
		phoneOperatorCodeRepository.save(new PhoneOperatorCode("097"));
		phoneOperatorCodeRepository.save(new PhoneOperatorCode("096"));
		phoneOperatorCodeRepository.save(new PhoneOperatorCode("095"));
		phoneOperatorCodeRepository.save(new PhoneOperatorCode("094"));
		phoneOperatorCodeRepository.save(new PhoneOperatorCode("093"));
		phoneOperatorCodeRepository.save(new PhoneOperatorCode("067"));
		phoneOperatorCodeRepository.save(new PhoneOperatorCode("068"));
		phoneOperatorCodeRepository.save(new PhoneOperatorCode("066"));
	}
}
