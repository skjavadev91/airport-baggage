package com.baggage.service;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.baggage.cnsts.Gate;
import com.baggage.exception.BaggageException;
import com.baggage.models.Conveyor;
import com.baggage.models.Node;


public class ConveryorSystemServiceImpl implements ConveryorSystemService{
	
	private EnumMap<Gate, Node> nodes = new EnumMap<Gate, Node>(Gate.class);
	@Override
	public Map<Node, List<Conveyor>> getConveyorsMap(List<String> converyorSystemInputList) throws BaggageException {
		Map<Node, List<Conveyor>> conveyors = new LinkedHashMap<Node, List<Conveyor>>();
		
			if(!CollectionUtils.isEmpty(converyorSystemInputList)) {
				converyorSystemInputList.stream().forEach(converyor -> {
					String splitter[] = converyor.split(" ");
					if(splitter.length != 3) {
							throw new BaggageException(" Input for conveyor is not in proper format "+ converyor);
					}else {
						String fromNode = splitter[0];
	                    	String toNode = splitter[1];
	                    int cost = Integer.parseInt(splitter[2]);

	                    Gate fromGate = Gate.getGate(fromNode);
	                    Gate toGate = Gate.getGate(toNode);

	                    if (fromGate == null || toGate == null) {
								throw new BaggageException(" Gate not available ");
	                    }
	                    
	                    Node from = createNode(fromGate, nodes);
	                    Node to = createNode(toGate, nodes);
	                    if(conveyors.containsKey(from)){
	                    	conveyors.get(from).add(new Conveyor(from, to, cost));
	                    }else{
	                    	List<Conveyor> conveyorsList = new ArrayList<Conveyor>();
	                    	conveyorsList.add(new Conveyor(from, to, cost));
	                    	conveyors.put(from, conveyorsList);
	                    }
	                    
	                    
	                    if(conveyors.containsKey(to)){
	                    	conveyors.get(to).add(new Conveyor(to, from, cost));
	                    }else{
	                    	List<Conveyor> conveyorsList = new ArrayList<Conveyor>();
	                    	conveyorsList.add(new Conveyor(to, from, cost));
	                    	conveyors.put(to, conveyorsList);
	                    }
					}
				});
			}
		
		return conveyors;
	}
	
	private Node createNode(Gate gate, EnumMap<Gate, Node> nodeMap) {
        if (nodeMap.containsKey(gate)) {
            return nodeMap.get(gate);
        }
        Node conveyorNode = new Node(gate, gate.getValue());
        nodeMap.put(gate, conveyorNode);
        return conveyorNode;
    }

}
