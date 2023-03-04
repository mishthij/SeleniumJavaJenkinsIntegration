package com.techarchive.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	
        plugin = {"pretty",
                "html:target/report/cucumber.html",
                "json:target/cucumber.json"
        },
        features = {"src/test/resources/features/test.feature"},
        tags="@Firstest",
        glue = {"com.techarchive.steps"}
       
)

public class CukeRunner {
}