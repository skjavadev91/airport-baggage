package com.baggage.models;

import com.baggage.cnsts.Gate;

public class Baggage  extends Base{
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8150382777863303066L;
	private String baggageId;
    private Gate entryPoint;
    private String flightId;
    
    
    public Baggage() {
		
	}
    
    public Baggage(String baggageId, Gate entryPoint, String flightId) {
		super();
		this.baggageId = baggageId;
		this.entryPoint = entryPoint;
		this.flightId = flightId;
	}
    
    
	/**
	 * @return the baggageId
	 */
	public String getBaggageId() {
		return baggageId;
	}
	/**
	 * @param baggageId the baggageId to set
	 */
	public void setBaggageId(String baggageId) {
		this.baggageId = baggageId;
	}
	/**
	 * @return the entryPoint
	 */
	public Gate getEntryPoint() {
		return entryPoint;
	}
	/**
	 * @param entryPoint the entryPoint to set
	 */
	public void setEntryPoint(Gate entryPoint) {
		this.entryPoint = entryPoint;
	}
	/**
	 * @return the flightId
	 */
	public String getFlightId() {
		return flightId;
	}
	/**
	 * @param flightId the flightId to set
	 */
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Baggage [baggageId=" + baggageId + ", entryPoint=" + entryPoint + ", flightId=" + flightId + "]";
	}

    
}
