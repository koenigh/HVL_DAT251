package caseStudy.version2_PlayTypeObjectification.playStructure;

public class Tragedy extends PlayType {
	@Override
	public int determineAmount(int audience) {
		return 40000 + (audience > 30 ? 1000 * (audience - 30) : 0);
	}
	@Override
	public int volumeCredits(int audience) {
		return this.volumeCreditsBase(audience);
	}

}
