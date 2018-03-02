package gmpUtils;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
				
              features = "src/test/resources/testcases"
              ,glue={"gmpStepDefn"}
              ,tags = "@SmokeTests"
              ,plugin = {"pretty", "html:target/cucumber-html-report"}
              ,monochrome = true
              )
public class RunnerClass{
 
}

