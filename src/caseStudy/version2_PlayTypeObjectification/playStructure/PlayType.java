package caseStudy.version2_PlayTypeObjectification.playStructure;

public abstract class PlayType{
/**
 * Determine amount for a given 
 * @param audience 
 * for this type of play
 */
	public abstract int determineAmount(int audience);
/**
 * Determine volume credits for a given 
 * @param audience
 * for this type of play	
 * 
 */
	public abstract int volumeCredits(int audience);
/**
 * Determine the base volume credits for a given audience
 */
	public int volumeCreditsBase(int audience) {
		return Math.max(audience - 30, 0);
	}
}
