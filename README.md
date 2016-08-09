# sikuli-regression-tests
Public Sikuli API Regression test pack

**still experimental and under developement - may change without any notice**

Usage:
- have a valid Maven 3 install
- download the repo
- Project Lombok needs to be intergated with your IDE tio enable debugging. Instructions here:
<a href="http://jnb.ociweb.com/jnb/jnbJan2010.html">Lombok Installation Instructions</a>

<b>Running full test suite (from command line)</b>
<p>In project root folder (where the `pom.xml` file is) run `mvn test`.</p>

<b>Running a single scenario (from IDE)</b>
- Find the scenario you want to run in the `*.feature` files.
- Annotate the scenario with `@wip`.
- Run the `FunctionalDev`.

<b>Running a group of scenarios (from IDE)</b>
<p>Each feature file has one or more tags (first line of the file). 
To run a group of all scenarios with a particular tag, create a new jUnit runner and add tag name in `tags` parameter of `@CucumberOptions`.</p>