package bowling;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/bowling"},
        glue = {"bowling"},
        plugin = {"pretty"}
)
public class BowlingScoreTest {
}
