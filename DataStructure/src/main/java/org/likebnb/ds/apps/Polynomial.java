package org.likebnb.ds.apps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Polynomial {
	final static private Logger LOG = LoggerFactory.getLogger(Polynomial.class);	
	
	private int degree;
	private int termCount;
	private Term[] polyTerms;
	private Term[] tempTerms;
	
	Polynomial() {
		this.degree = 0;
		this.termCount = 0;
		this.polyTerms = new Term[0];
	}
	
	public void addTerm(Term term) {
		if ((term.getExponent() + term.getCoefficient()) == 0) 
			return;
		
		int pos = termCount;
		for (int idx = 0; idx < termCount; idx++) {
			if (term.getExponent() > this.getTerms()[idx].getExponent()) {
				pos = idx;
				break;
			}
		}
		addTerm(pos, term);
	}

	private void addTerm(int pos, Term term) {
		termCount++;
		tempTerms = new Term[termCount];
		for (int idx = 0; idx < termCount; idx++) {
			if (idx < pos) {
				tempTerms[idx] = polyTerms[idx];
			} else if (idx == pos) {
				tempTerms[idx] = term;
			} else {
				tempTerms[idx] = polyTerms[idx - 1];
			}
		}
		polyTerms = tempTerms.clone();
		setDegree();
	}
	
	public void removeTerm(int exp) {
		int pos = -1;
		try {
			Term t = new Term(1, exp);
			pos = findTerm(t, this);
			polyTerms[pos].setZero();
			removeTerm();
		} catch (ArrayIndexOutOfBoundsException e) {
			LOG.warn("입력하신 위치({})는 배열의 첨자 범위({})를 벗어납니다.", 
			          new Object[] {pos, termCount, e});
		}
	}
	
	private void removeTerm() {
		termCount--;
		tempTerms = new Term[termCount];
		int jdx = 0;
		for (int idx = 0; idx < polyTerms.length; idx++) {
			if (polyTerms[idx].getTerm() != null) {
				tempTerms[jdx] = polyTerms[idx];
				jdx++;
			} 
		}
		polyTerms = tempTerms.clone();
		setDegree();		
	}
	
	public void addPoly(Polynomial p1, Polynomial p2) {
		for (Term t: p1.getTerms()) { this.addTerm(t); }
		
		int pos = -1;
		for (Term t2: p2.getTerms()) {
			pos = findTerm(t2, this);
			if (pos > -1) {
				Term t1 = this.getTerms()[pos];
				Term newTerm = new Term(t1.getCoefficient() + t2.getCoefficient(), 
						                t1.getExponent());
				this.getTerms()[pos] = newTerm;
			} else {
				this.addTerm(t2);
			}
		}
	}
	
	private boolean isSameExp(Term t1, Term t2) {
		return t1.getExponent() == t2.getExponent();
	}
	
	public int findTerm(Term t, Polynomial p) {
		for (int idx = 0; idx < p.getTermCount(); idx++) {
			if (isSameExp(t, p.getTerms()[idx])) {
				return idx;
			}
		}
		
		return -1;
	}
	
	private void setDegree() {
		this.degree = 0;
		for (Term term: polyTerms) {
			if (degree < term.getExponent()) {
				this.degree = term.getExponent();
			}
		}
	}
	
	public String printTerm() {
		StringBuffer sb = new StringBuffer();
		for (int idx = 0; idx < termCount ; idx++) {
			sb.append(polyTerms[idx].printTerm());
			if ((idx < termCount - 1) && !polyTerms[idx + 1].printTerm().equals("")) {
				sb.append(" + ");
			} 
		}
		
		return sb.toString();
	}
	
	public int getDegree() {
		return this.degree;
	}
	
	public int getTermCount() {
		return this.termCount;
	}
	
	public Term[] getTerms() {
		return polyTerms;
	}
	
	public String printPolyAsArray() {
		List<String> list = new ArrayList<String>();
		for (Term term: polyTerms) {
			list.add(Arrays.toString(term.getTerm()));
		}
		
		return list.toString();
	}
	
	public void printSummary(String msg) {
		LOG.info("-------------------------------------------------------------");
		LOG.info("  {}", msg);
		LOG.info("-------------------------------------------------------------");
		LOG.info("          Polynomial: {}", this.printTerm());
		LOG.info("              Degree: {}", this.getDegree());
		LOG.info("           TermCount: {}", this.getTermCount());
		LOG.info("        Terms(Array): {}", this.printPolyAsArray());
		LOG.info("    Polynomial(ref.): {}", this);
		LOG.info("         Terms(ref.): {}", Arrays.toString(this.getTerms()));
		LOG.info("-------------------------------------------------------------\n");
	}
}
