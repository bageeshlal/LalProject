# LalProject
This is a mavan project. Automation is done using Selenium webdriver, testng and java, javascript. 
This project can be imported to eclipse. Run by right clicking testng.xnl --> Run As --> Testng suite. 
Testng project can be run from command prompt also with configurning class path to pick correct testng.jar and class files

Running project from command line
download the updated pom.xml
open command prompt and navigate to project home directory
run following command
mvn test
The execution report will be present under target/surefire-reports folder

pom.xml has been updated to use selenium 3.0. With latest selenium, we need to set the system property for each browser. 
For Firefox we need to set to geckodriver.
First download geckodriver and provide the folder path in path variable of environment variable.
Or
provide code. Place holder is given in following files - ABCNewsBase.java, ABCRadioBase.java and JSONBase.java. Currently the statement to set Sytem.setProperty has been commented.Uncomment the line and give the complete exe path

