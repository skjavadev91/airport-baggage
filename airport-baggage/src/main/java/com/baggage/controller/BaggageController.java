package com.baggage.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.baggage.cnsts.BaggageConstants;
import com.baggage.cnsts.Gate;
import com.baggage.exception.BaggageException;
import com.baggage.models.Baggage;
import com.baggage.models.Conveyor;
import com.baggage.models.FlightDeparture;
import com.baggage.models.Node;
import com.baggage.service.BaggageSystemService;
import com.baggage.service.BaggageSystemServiceImpl;
import com.baggage.service.ConveryorSystemService;
import com.baggage.service.ConveryorSystemServiceImpl;
import com.baggage.service.DepartureFlightService;
import com.baggage.service.DepartureFlightServiceImpl;

public class BaggageController {

	private boolean isConveryorSystem = false;
	private boolean isBaggageSystem = false;
	private boolean isFlightDepartureSystem = false;

	private Map<String, Baggage> bagIdToBagMap;
	private Map<String, FlightDeparture> flightIdToDepartureMap;
	private Map<Node, List<Conveyor>> conveyorsMap;

	private List<String> converyorInputList = null;
	private List<String> baggageInputList = null;
	private List<String> flightInputList = null;

	private ConveryorSystemService converyorSystemService;
	private BaggageSystemService baggageSystemService;
	private DepartureFlightService departureFlightService;

	/**
	 * Process the input file and create the system maps
	 * 
	 * @throws BaggageException
	 */
	public void processInput() throws BaggageException {


		
		BufferedReader buffer = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("BaggageInput.txt")));
		
		try (Stream<String> lines = buffer.lines()) {
			converyorSystemService = new ConveryorSystemServiceImpl();
			baggageSystemService = new BaggageSystemServiceImpl();
			departureFlightService = new DepartureFlightServiceImpl();

			lines.forEach(line -> {
				if (StringUtils.isEmpty(line)) {
					return;
				}

				if (line.startsWith("# Section:")) {
					if (line.endsWith("Departures")) {
						isFlightDepartureSystem = true;
						isBaggageSystem = false;
						isConveryorSystem = false;
						flightInputList = new ArrayList<String>();
						return;
					} else if (line.endsWith("Conveyor System")) {
						isConveryorSystem = true;
						isFlightDepartureSystem = false;
						isBaggageSystem = false;
						converyorInputList = new ArrayList<String>();
						return;
					} else if (line.endsWith("Bags")) {
						isConveryorSystem = false;
						isFlightDepartureSystem = false;
						isBaggageSystem = true;
						baggageInputList = new ArrayList<String>();
						return;
					}
				}

				if (isFlightDepartureSystem && !isBaggageSystem && !isConveryorSystem) {
					flightInputList.add(line);
				}

				if (!isFlightDepartureSystem && isBaggageSystem && !isConveryorSystem) {
					baggageInputList.add(line);
				}

				if (!isFlightDepartureSystem && !isBaggageSystem && isConveryorSystem) {
					converyorInputList.add(line);
				}

			});
			setConveyorsMap(converyorSystemService.getConveyorsMap(converyorInputList));
			setBagIdToBagMap(baggageSystemService.getBaggages(baggageInputList));
			setFlightIdToDepartureMap(departureFlightService.getDepartureFlights(flightInputList));

		}catch(Exception ex) {
			throw new BaggageException(ex);
		}
	}

	/**
	 * Method to find the route of the Baggage.
	 * 
	 * @param conveyorGraph
	 * @param baggageMap
	 * @param departureFlightsMap
	 * @return String
	 */
	public String routeBaggages(Map<Node, List<Conveyor>> conveyorsMap, Map<String, Baggage> baggageMap,
			Map<String, FlightDeparture> departureFlightsMap) {
		StringBuilder sb = new StringBuilder();

		try {
			baggageMap.entrySet().stream().forEach(baggage -> {
				Baggage bag = baggage.getValue();
				String bagId = bag.getBaggageId();
				String flightId = bag.getFlightId();
				Gate sourceGate = bag.getEntryPoint();

				sb.append(bagId + " ");

				Gate departureGate = null;
				if (flightId.equals(BaggageConstants.ARRIVAL)) {
					departureGate = Gate.BAGGAGE_CLAIM;
				} else {
					departureGate = departureFlightsMap.get(flightId).getDepartureGate();
				}

				Node sourceNode = new Node(sourceGate, sourceGate.getValue());
				Node targetNode = new Node(departureGate, departureGate.getValue());
				List<Node> routeNodes = getBaggageRoute(conveyorsMap, sourceNode, targetNode);

				if (!CollectionUtils.isEmpty(routeNodes)) {
					sb.append(sourceGate.getValue() + " ");
					Node prevNode = routeNodes.get(0);
					sb.append(prevNode.getNodeId().getValue() + " ");

					for (int i = 1; i < routeNodes.size(); i++) {
						Node current = routeNodes.get(i);
						prevNode = current;
						sb.append(current.getNodeId().getValue() + " ");
					}
					sb.append(": " + prevNode.getMinDistance());
					sb.append(System.lineSeparator());
				} else {
					sb.append("No path exist for this baggage");
					sb.append(System.lineSeparator());
				}
			});
		} catch (Exception ex) {
			throw new BaggageException(ex);
		}

		return sb.toString();

	}

	
	 public List<Node> getBaggageRoute(Map<Node, List<Conveyor>> conveyorsMap, Node sourceNode, Node targetNode) {
	        List<Node> shortestPath = new ArrayList<>();

	        sourceNode.setMinDistance(0);

	        PriorityBlockingQueue<Node> queue = new PriorityBlockingQueue<>();
	        
	        Set<Node> nodes = new HashSet<Node>(conveyorsMap.keySet()); 
	        		
	        nodes.stream().forEach(node -> {
	        if (!node.equals(sourceNode)) {
	        		node.setMinDistance(Integer.MAX_VALUE);
	        		node.setPrevious(null);
	            } else {
	            	node = sourceNode;
	            }
	            queue.add(node);
	        });
	     

	        while (!queue.isEmpty()) {
	            Node node = queue.poll();

	            if (node.equals(targetNode)) {
	                while (node.getPrevious() != null) {
	                    shortestPath.add(node);
	                    node = node.getPrevious();
	                }
	                break;
	            }

	            queue.remove(node);

	            List<Conveyor> edges = conveyorsMap.get(node);

	            for (Conveyor edge : edges) {
	                Node v = edge.getToNode();

	                Integer weight = edge.getDistance();
	                Integer distanceThroughU = node.getMinDistance() + weight;

	                if (distanceThroughU < v.getMinDistance()) {
	                    v.setMinDistance(distanceThroughU);
	                    v.setPrevious(node);
	                    queue.remove(v);
	                    queue.add(v);
	                }
	            }
	        }

	        Collections.reverse(shortestPath);

	        return shortestPath;
	    }

	

	/**
	 * @return the bagIdToBagMap
	 */
	public Map<String, Baggage> getBagIdToBagMap() {
		return bagIdToBagMap;
	}

	/**
	 * @param bagIdToBagMap
	 *            the bagIdToBagMap to set
	 */
	public void setBagIdToBagMap(Map<String, Baggage> bagIdToBagMap) {
		this.bagIdToBagMap = bagIdToBagMap;
	}

	/**
	 * @return the flightIdToDepartureMap
	 */
	public Map<String, FlightDeparture> getFlightIdToDepartureMap() {
		return flightIdToDepartureMap;
	}

	/**
	 * @param flightIdToDepartureMap
	 *            the flightIdToDepartureMap to set
	 */
	public void setFlightIdToDepartureMap(Map<String, FlightDeparture> flightIdToDepartureMap) {
		this.flightIdToDepartureMap = flightIdToDepartureMap;
	}

	/**
	 * @return the converyorInputList
	 */
	public List<String> getConveryorInputList() {
		return converyorInputList;
	}

	/**
	 * @param converyorInputList
	 *            the converyorInputList to set
	 */
	public void setConveryorInputList(List<String> converyorInputList) {
		this.converyorInputList = converyorInputList;
	}

	/**
	 * @return the baggageInputList
	 */
	public List<String> getBaggageInputList() {
		return baggageInputList;
	}

	/**
	 * @param baggageInputList
	 *            the baggageInputList to set
	 */
	public void setBaggageInputList(List<String> baggageInputList) {
		this.baggageInputList = baggageInputList;
	}

	/**
	 * @return the flightInputList
	 */
	public List<String> getFlightInputList() {
		return flightInputList;
	}

	/**
	 * @param flightInputList
	 *            the flightInputList to set
	 */
	public void setFlightInputList(List<String> flightInputList) {
		this.flightInputList = flightInputList;
	}

	/**
	 * @return the conveyorsMap
	 */
	public Map<Node, List<Conveyor>> getConveyorsMap() {
		return conveyorsMap;
	}

	/**
	 * @param conveyorsMap the conveyorsMap to set
	 */
	public void setConveyorsMap(Map<Node, List<Conveyor>> conveyorsMap) {
		this.conveyorsMap = conveyorsMap;
	}

}
