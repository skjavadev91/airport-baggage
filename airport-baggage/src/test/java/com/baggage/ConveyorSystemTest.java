package com.baggage;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.baggage.controller.BaggageController;
import com.baggage.models.Conveyor;
import com.baggage.models.Node;
import com.baggage.service.BaggageSystemService;
import com.baggage.service.BaggageSystemServiceImpl;
import com.baggage.service.ConveryorSystemService;
import com.baggage.service.ConveryorSystemServiceImpl;
import com.baggage.service.DepartureFlightService;
import com.baggage.service.DepartureFlightServiceImpl;

import junit.framework.TestCase;

public class ConveyorSystemTest extends TestCase {
	
	
	@Test
	public void testConveryorSystemInput() {
		BaggageController baggageController = new BaggageController();
		baggageController.processInput();
		ConveryorSystemService converyorSystemService = new ConveryorSystemServiceImpl();
		Map<Node, List<Conveyor>> conveyors  = converyorSystemService.getConveyorsMap(baggageController.getConveryorInputList());
		System.out.println(conveyors);

	}
	
	@Test
	public void testBaggageInput() {
		BaggageController baggageController = new BaggageController();
		baggageController.processInput();
		BaggageSystemService baggageSystemService = new BaggageSystemServiceImpl();
		System.out.println(baggageSystemService.getBaggages(baggageController.getBaggageInputList()));

	}
	
	
	@Test
	public void testDepartureFlightInput() {
		BaggageController baggageController = new BaggageController();
		baggageController.processInput();
		DepartureFlightService departureFlightService = new DepartureFlightServiceImpl();
		System.out.println(departureFlightService.getDepartureFlights(baggageController.getFlightInputList()));

	}
	
}
