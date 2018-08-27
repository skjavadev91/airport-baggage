package com.baggage.service;

import java.util.List;
import java.util.Map;

import com.baggage.exception.BaggageException;
import com.baggage.models.Baggage;

public interface BaggageSystemService {
	
	public Map<String, Baggage> getBaggages(List<String> baggages) throws BaggageException;

}
