package org.likebnb.ds.apps;

public class TermNode extends Term {

	private TermNode link;
	
	TermNode() {
		super();
		this.link = null;
	}
	
	TermNode(int coefficient) {
		super(coefficient);
		this.link = null;
	}
	
	TermNode(int coefficient, int exponent) {
		super(coefficient, exponent);
		this.link = null;
	}
	
	public TermNode getLink() {
		return this.link;
	}
	
	public void setLink(TermNode link) {
		this.link = link;
	}
	
	public Term getData() {
		return this;
	}
	
	public void setData(Term term) {
		this.setTerm(term);
	}
	
	@Override
	public String toString() {
		return this.printTerm();
	}
}
