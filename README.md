# sikuli-regression-tests
Public Sikuli API Regression test pack

**still experimental and under developement - may change without any notice**

Usage:
- have a valid Maven 3 install
- download the repo
- Project Lombok needs to be intergated with your IDE tio enable debugging. Instructions here:
<a href="http://jnb.ociweb.com/jnb/jnbJan2010.html">Lombok Installation Instructions</a>

<b>Running full tests suite</b>
In project root folder (where the `pom.xml` file is) run `mvn test`.

<b>Running a single scenario</b>
- Find the scenario you want to run in the `*.feature` files.
- Annotate the scenario with `@wip`.
- Run the `FunctionalDev`.