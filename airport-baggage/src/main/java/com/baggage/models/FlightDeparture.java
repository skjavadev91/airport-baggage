package com.baggage.models;

import com.baggage.cnsts.Gate;

public class FlightDeparture extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8111242852214072530L;
	private String flightNbr;
	private Gate departureGate;
	private String departureStation;
	private String departureTime;

	public FlightDeparture() {

	}

	public FlightDeparture(String flightNbr, Gate departureGate, String departureStation, String departureTime) {
		super();
		this.flightNbr = flightNbr;
		this.departureGate = departureGate;
		this.departureStation = departureStation;
		this.departureTime = departureTime;
	}

	/**
	 * @return the flightNbr
	 */
	public String getFlightNbr() {
		return flightNbr;
	}

	/**
	 * @param flightNbr
	 *            the flightNbr to set
	 */
	public void setFlightNbr(String flightNbr) {
		this.flightNbr = flightNbr;
	}

	/**
	 * @return the departureGate
	 */
	public Gate getDepartureGate() {
		return departureGate;
	}

	/**
	 * @param departureGate
	 *            the departureGate to set
	 */
	public void setDepartureGate(Gate departureGate) {
		this.departureGate = departureGate;
	}

	/**
	 * @return the departureStation
	 */
	public String getDepartureStation() {
		return departureStation;
	}

	/**
	 * @param departureStation
	 *            the departureStation to set
	 */
	public void setDepartureStation(String departureStation) {
		this.departureStation = departureStation;
	}

	/**
	 * @return the departureTime
	 */
	public String getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param departureTime
	 *            the departureTime to set
	 */
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlightDeparture [flightNbr=" + flightNbr + ", departureGate=" + departureGate + ", departureStation="
				+ departureStation + ", departureTime=" + departureTime + "]";
	}

}
