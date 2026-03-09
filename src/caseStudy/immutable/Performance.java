package caseStudy.immutable;

public class Performance {
	private String playId;
	private int audience;
	public Performance(String playId, int audience) {
		super();
		this.playId = playId;
		this.audience = audience;
	}
	public String getPlayId() {
		return this.playId;
	}
	public int getAudience() {
		return this.audience;
	} 
}
