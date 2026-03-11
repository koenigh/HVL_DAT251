package caseStudy.version2_PlayTypeObjectification.playStructure;

public class Comedy extends PlayType {
    @Override
    public int determineAmount(int audience) {
        return 30000 + (audience > 20 ? 10000 + 500 * (audience - 20) : 0);
    }
    @Override
    public int volumeCredits(int audience) {
        return this.volumeCreditsBase(audience) + audience / 5;
    }
}

