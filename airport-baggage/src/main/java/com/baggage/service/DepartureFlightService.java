package com.baggage.service;

import java.util.List;
import java.util.Map;

import com.baggage.exception.BaggageException;
import com.baggage.models.FlightDeparture;


public interface DepartureFlightService {

	 public Map<String, FlightDeparture> getDepartureFlights(List<String> departureFlights) throws BaggageException;
}
