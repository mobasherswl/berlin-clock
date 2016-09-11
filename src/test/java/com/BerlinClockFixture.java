package com;

import com.clock.BerlinClock;
import com.clock.TimeConverter;
import com.support.BehaviouralTestEmbedder;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BerlinClockFixture {

    private TimeConverter berlinClock = BerlinClock.getInstance();
    private String theTime;

    @Test
    public void berlinClockAcceptanceTests() throws Exception {
        BehaviouralTestEmbedder.aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("berlin-clock.story")
                .run();
    }

    @When("the time is $time")
    public void whenTheTimeIs(String time) {
        theTime = time;
    }

    @Then("the clock should look like $")
    public void thenTheClockShouldLookLike(String theExpectedBerlinClockOutput) {
        assertThat(berlinClock.convertTime(theTime)).isEqualTo(theExpectedBerlinClockOutput);
    }
}
