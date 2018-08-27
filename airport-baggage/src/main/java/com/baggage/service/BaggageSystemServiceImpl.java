package com.baggage.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.baggage.cnsts.Gate;
import com.baggage.exception.BaggageException;
import com.baggage.models.Baggage;

public class BaggageSystemServiceImpl implements BaggageSystemService{

	@Override
	public Map<String, Baggage> getBaggages(List<String> baggages) throws BaggageException{
		Map<String, Baggage> baggageMap = new LinkedHashMap<String, Baggage>();
		
		if(!CollectionUtils.isEmpty(baggages)) {
			baggages.stream().forEach(bag -> {
				String baggage[] = bag.split(" ");
                if (baggage.length != 3) {
						throw new BaggageException("Input is not in proper format : "+ bag );
                }else{
                	String bagId = baggage[0];
                    Gate entryGate = Gate.getGate(baggage[1]);
                    String flightId = baggage[2];

                    if (entryGate == null) {
    						throw new BaggageException("Gate not available ");
                    }

                    baggageMap.put(bagId, new Baggage(bagId, entryGate, flightId));
                }
			});
			
		}
		return baggageMap;
	}

}
