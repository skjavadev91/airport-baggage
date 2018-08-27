package com.baggage.models;

import com.baggage.cnsts.Gate;

public class Node extends Base implements Comparable<Node> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5010384630566891103L;
	private Gate nodeId;
	private String nodeName;
	private Integer minDistance = Integer.MAX_VALUE;
	private Node previous;

	public Node(Gate nodeId, String nodeName) {
		this.nodeId = nodeId;
		this.nodeName = nodeName;
	}

	/**
	 * @return the nodeId
	 */
	public Gate getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId
	 *            the nodeId to set
	 */
	public void setNodeId(Gate nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * @param nodeName
	 *            the nodeName to set
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	

	/**
	 * @return the minDistance
	 */
	public Integer getMinDistance() {
		return minDistance;
	}

	/**
	 * @param minDistance the minDistance to set
	 */
	public void setMinDistance(Integer minDistance) {
		this.minDistance = minDistance;
	}

	/**
	 * @return the previous
	 */
	public Node getPrevious() {
		return previous;
	}

	/**
	 * @param previous
	 *            the previous to set
	 */
	public void setPrevious(Node previous) {
		this.previous = previous;
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
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
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
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;
		if (nodeId != other.nodeId)
			return false;
		return true;
	}

	@Override
	public int compareTo(Node other) {
		return Double.compare(minDistance, other.minDistance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [nodeId=" + nodeId + ", nodeName=" + nodeName + ", minDistance=" + minDistance + ", previous="
				+ previous + "]";
	}

}
