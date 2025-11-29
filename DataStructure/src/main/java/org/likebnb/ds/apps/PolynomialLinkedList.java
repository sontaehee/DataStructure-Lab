package org.likebnb.ds.apps;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PolynomialLinkedList {
	final static private Logger LOG = LoggerFactory.getLogger(PolynomialLinkedList.class);	

	private int degree;
	private int termCount;
	private TermNode head;

	PolynomialLinkedList() {
		this.degree = 0;
		this.termCount = 0;
		this.head = new TermNode();
	}

	public int getDegree() {
		return this.degree;
	}

	public int getTermCount() {
		return this.termCount;
	}

	public void addTerm(TermNode node) {

		LOG.info("--------------------------------------");
		LOG.info(" newNode: {}", node.getData());
		LOG.info("--------------------------------------");

		TermNode prevNode = head;
		TermNode nextNode = prevNode != null ? prevNode.getLink() : null;
		while (nextNode != null) {
			LOG.info("1. prevNode({}), nextNode({})", prevNode.getData(), nextNode.getData());

			if (node.getExponent() > nextNode.getExponent()) {
				LOG.info("2. newNode({}) > nextNode({})", node.getData(), nextNode.getData());
				LOG.info("    +---> insert before nextNode({})", nextNode.getData());

				node.setLink(nextNode);
				prevNode.setLink(node);
				this.termCount++;
				break;
			} else if (node.getExponent() == nextNode.getExponent()) {
				LOG.info("2. newNode({}) == nextNode({})", node.getData(), nextNode.getData());
				LOG.info("    +---> update nextNode({})", nextNode.getData());

				Term term = new Term(nextNode.getCoefficient() + node.getCoefficient(), node.getExponent());
				nextNode.setData(term);
				break;
			} else if (node.getExponent() < nextNode.getExponent()) {
				if (nextNode.getLink() == null) {
					LOG.info("2. newNode({}) < nextNode({}) and nextNode.getLink() is null", node.getData(), nextNode.getData());
					LOG.info("    +---> append after nextNode({}), because it's last!", nextNode.getData());

					nextNode.setLink(node);
					node.setLink(null);
					this.termCount++;
					break;
				} else {
					LOG.info("3. fetch nextNode:{}", nextNode.getLink());

					prevNode = nextNode;
					nextNode = nextNode.getLink();
				}
			}
		}

		if (this.termCount == 0) {
			LOG.info("1. prevNode({}), nextNode({})", prevNode.getLink(), nextNode);
			LOG.info("2. There is no term, termCount({}).", termCount);
			LOG.info("    +---> append newNode({}) after head", node.getData());
			this.head.setLink(node);
			this.termCount++;
		}
		this.degree = head.getLink().getExponent();
		printSummarization(node);
	}
	
	public void removeTerm(int exponent) {
		LOG.info("--------------------------------------");
		LOG.info(" removeNode: {}", exponent);
		LOG.info("--------------------------------------");

		TermNode prevNode = head;
		TermNode nextNode = prevNode != null ? prevNode.getLink() : null;
		while (nextNode != null) {
			LOG.info("1. prevNode({}), nextNode({})", prevNode.getData(), nextNode.getData());
			if (exponent == nextNode.getExponent()) {
				LOG.info("2. removeNode({}) == nextNode({})", exponent, nextNode.getData());
				LOG.info("    +---> remove nextNode({})", nextNode.getData());
				prevNode.setLink(nextNode.getLink());
				nextNode = null;
				this.termCount--;
				break;
			} else {
				LOG.info("3. fetch nextNode:{}", nextNode.getLink());
				prevNode = nextNode;
				nextNode = nextNode.getLink();
			}
		}
		if (this.termCount > 0) {
			this.degree = head.getLink().getExponent();
		} else {
			this.degree = 0;
		}
		printSummarization(exponent);
	}

	public PolynomialLinkedList addPoly(PolynomialLinkedList p1, PolynomialLinkedList p2) {
		TermNode node = p1.head;
		while(node.getLink() != null) {
			node = node.getLink();
			this.addTerm(new TermNode(node.getCoefficient(), node.getExponent()));
		}
		
		node = p2.head;
		while(node.getLink() != null) {
			node = node.getLink();
			this.addTerm(new TermNode(node.getCoefficient(), node.getExponent()));
		}
				
		return this;
	}
	
	private void printSummarization(Object obj) {
		LOG.info("+Summarization of new Poly------------");
		
		if (obj instanceof TermNode) 
			LOG.info("     addTerm: {}", obj);
		else 
			LOG.info("  removeTerm: {}", obj);
		
		LOG.info("   termCount: {}", this.termCount);
		LOG.info("      degree: {}", this.degree);
		LOG.info("   polyTerms: {}", this);
		LOG.info("--------------------------------------\n");
	}

	public TermNode getHead() {
		return this.head;
	}

	@Override
	public String toString() {
		TermNode currNode = head;
		StringBuffer sb = new StringBuffer();
		while (currNode.getLink() != null) {
			currNode = currNode.getLink();
			sb.append(Arrays.toString(currNode.getTerm()));
		}
		return sb.toString();
	}

	public String printPoly() {
		TermNode currNode = head;
		StringBuffer sb = new StringBuffer("P(X) = ");
		while (currNode.getLink() != null) {
			currNode = currNode.getLink();
			sb.append(currNode.printTerm());
			
			if (currNode.getLink() != null) {
				sb.append(" + ");
			}
		}
		return sb.toString();
	}
}
