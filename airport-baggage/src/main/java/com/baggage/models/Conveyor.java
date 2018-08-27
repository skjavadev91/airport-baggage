package com.baggage.models;

public final class Conveyor extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4049178751628246590L;
	private Node fromNode;
	private Node toNode;
	private Integer distance;

	public Conveyor(Node from, Node to, Integer distance) {
		super();
		this.fromNode = from;
		this.toNode = to;
		this.distance = distance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fromNode == null) ? 0 : fromNode.hashCode());
		result = prime * result + ((toNode == null) ? 0 : toNode.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Conveyor))
			return false;
		Conveyor other = (Conveyor) obj;
		if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance))
			return false;
		if (fromNode == null) {
			if (other.fromNode != null)
				return false;
		} else if (!fromNode.equals(other.fromNode))
			return false;
		if (toNode == null) {
			if (other.toNode != null)
				return false;
		} else if (!toNode.equals(other.toNode))
			return false;
		return true;
	}

	/**
	 * @return the fromNode
	 */
	public Node getFromNode() {
		return fromNode;
	}

	/**
	 * @param fromNode
	 *            the fromNode to set
	 */
	public void setFromNode(Node fromNode) {
		this.fromNode = fromNode;
	}

	/**
	 * @return the toNode
	 */
	public Node getToNode() {
		return toNode;
	}

	/**
	 * @param toNode
	 *            the toNode to set
	 */
	public void setToNode(Node toNode) {
		this.toNode = toNode;
	}

	
	/**
	 * @return the distance
	 */
	public Integer getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Conveyor [fromNode=" + fromNode + ", toNode=" + toNode + ", distance=" + distance + "]";
	}

}
