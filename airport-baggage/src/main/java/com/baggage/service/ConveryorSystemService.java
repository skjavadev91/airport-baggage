package com.baggage.service;

import java.util.List;
import java.util.Map;

import com.baggage.exception.BaggageException;
import com.baggage.models.Conveyor;
import com.baggage.models.Node;

public interface ConveryorSystemService {
	
	public Map<Node, List<Conveyor>> getConveyorsMap(List<String> converyorSystemInputList) throws BaggageException;
}
