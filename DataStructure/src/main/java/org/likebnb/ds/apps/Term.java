package org.likebnb.ds.apps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Term {
	final static private Logger LOG = LoggerFactory.getLogger(Term.class);	

	private int coefficient;
	private int exponent;
	private int[] term;
	
	Term() {
		this.coefficient = 0;
		this.exponent = 0;
		setTerm();
	}
	
	Term(int coefficient) {
		this.coefficient = coefficient;
		this.exponent = 0;
		setTerm();
	}
	
	Term(int coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
		setTerm();
	}

	private void setTerm() {
		if (this.exponent < 0) {
			LOG.info("지수({})는 0을 포함한 양의 정수이어야 합니다.", this.exponent);
			return;
		}
		
		this.term = new int[] {this.coefficient, this.exponent};
	}
	
	protected void setTerm(Term term) {
		this.coefficient = term.coefficient;
		this.exponent = term.exponent;
		setTerm();
	}

	public void setZero() {
		this.coefficient = 0;
		this.exponent = 0;
		this.term = null;
	}
	
	public int getCoefficient() {
		return this.coefficient;
	}
	
	public int getExponent() {
		return this.exponent;
	}
	
	public int[] getTerm() {
		return this.term;
	}

    public String printTerm() {
		if (coefficient == 0) return "";

		StringBuffer sb = new StringBuffer();
		switch(exponent) {
			case 0:
				sb.append(coefficient);
				break;
			case 1:
				sb.append(coefficient).append("X");
				break;
			default:
				sb.append(coefficient).append("X^").append(exponent);
		}

		return sb.toString();
    }
}