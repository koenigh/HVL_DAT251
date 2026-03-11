package caseStudy.version2_PlayTypeObjectification.view;

import caseStudy.version2_PlayTypeObjectification.playStructure.PlayType;

/**
 * A DTO for performance data
 */
public abstract class PerformanceView {
	protected String playName;
	protected PlayType playType;
	protected float amount;
	protected int audience;
	public PerformanceView(String playName, PlayType playType, float amount, int audience) {
		super();
		this.playName = playName;
		this.playType = playType;
		this.amount = amount;
		this.audience = audience;
	}
/**
 * @return s a textual representation of the given performance data 
 */
	public abstract String render();
}
