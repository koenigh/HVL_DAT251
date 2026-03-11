package caseStudy.version2_PlayTypeObjectification.view;

import caseStudy.version2_PlayTypeObjectification.playStructure.PlayType;

public class PerformanceViewPlainText extends PerformanceView {
	public PerformanceViewPlainText(String playName, PlayType playType, float amount, int audience) {
		super(playName, playType, amount, audience);
	}
	public String render() {
		return playName + ": " + amount + " €, (" + audience + " visitors).\n";
	}
}
