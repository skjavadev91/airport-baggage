package com.baggage.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.baggage.cnsts.Gate;
import com.baggage.exception.BaggageException;
import com.baggage.models.FlightDeparture;

public class DepartureFlightServiceImpl implements DepartureFlightService {

	@Override
	public Map<String, FlightDeparture> getDepartureFlights(List<String> departureFlights) throws BaggageException {

		Map<String, FlightDeparture> departureFlightMap = new LinkedHashMap<String, FlightDeparture>();
		if (!CollectionUtils.isEmpty(departureFlights)) {
			departureFlights.stream().forEach(flight -> {
				String departedFlights[] = flight.split(" ");
				if (departedFlights.length != 4) {
					throw new BaggageException(" Input for departure flights not in proper format : " + flight);
				} else {
					String flightId = departedFlights[0];
					String flightGate = departedFlights[1];
					String destination = departedFlights[2];
					String departureTime = departedFlights[3];

					Gate departureGate = Gate.getGate(flightGate);

					if (departureGate == null) {
						throw new BaggageException(" Gate not available ");
					}

					departureFlightMap.put(flightId,
							new FlightDeparture(flightId, departureGate, destination, departureTime));

				}
			});
		}
		return departureFlightMap;
	}

}
