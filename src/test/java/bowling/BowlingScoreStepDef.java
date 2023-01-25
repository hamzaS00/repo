package bowling;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class BowlingScoreStepDef {

    private BowlingService bowlingService = new BowlingService();
    private String pins;
    private int score;

    @Given("All pins missed")
    public void allPinsMissed() {
        pins = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    }

    @Given("the first frame is {string}")
    public void theFirstFrameIs(String inputFrame) {
        pins = inputFrame + ",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    }

    @Given("First pin of first throw fell")
    public void firstPinOfFirstThrowFell() {
        pins = "1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    }

    @Given("Two pins of first throw fell")
    public void twoPinsOfFirstThrowFell() {
        pins = "2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    }

    @When("I compute the score")
    public void iComputeTheScore() {
        score = bowlingService.computeScore(pins);
    }

    @Then("The score is {int}")
    public void theScoreIs(int expectedScore) {
        Assertions.assertThat(this.score).isEqualTo(expectedScore);
    }

    @Given("the frames are {string}")
    public void theFramesAre(String pins) {
        this.pins = pins;
    }
}
