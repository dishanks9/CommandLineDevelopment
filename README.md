# Food Truck Data From San Francisco Government's API
This is a simple Java based command line application which dispalys the food truck which are open on current day and current time.
The data will be displayed on the command line in the slots of 10 food trucks per page.

The information about the API used can be found [here](https://dev.socrata.com/foundry/data.sfgov.org/bbb8-hzi6).

## Pre-requisites for executing the application

- The first and most important requirement is that there should be Java 1.7 or later installed on your machine.
  - If Java is not installed kindly follow the steps given below:
    - For Mac: [Click here](https://stackoverflow.com/questions/24342886/how-to-install-java-8-on-mac)
    - For Ubuntu: [Click here](https://thishosting.rocks/install-java-ubuntu/)
    - For windows: [Click here](https://www.journaldev.com/476/java-windows-10-download-install).

## Executing the application
- Clone the repository into your local machine.

- Open the terminal/command prompt and navigate to the classFiles directory in the TakeHomeTest folder using the command:
  - For Ubuntu: cd TakeHomeTest/classFiles
  - For Windows: cd TakeHomeTest\classFiles
  
- To run the program execute the following commands given below:
  - For Ubuntu: 
      ```
      javac -cp "../ExternalJars/*:" -d . ../src/*.java
      java -cp "../ExternalJars/*:" Main
      ```
   - For Windows:
      ```
      javac -cp "..\ExternalJars\*;" -d . ..\src\*.java
      java -cp "..\ExternalJars\*;" Main
      ```
 
 - To run the test cases execute the following commands given below:
   - For Ubuntu:
      ```
      javac -cp "../ExternalJars/*:" -d . ../src/FoodTruck.java ../src/FoodTruckService.java ../test/*.java
      java -cp "../ExternalJars/*:" TestRunner
      ```
   - For Windows:
      ```
      javac -cp "..\ExternalJars\*;" -d . ..\src\FoodTruck.java ..\src\FoodTruckService.java  ..\test\*.java
      java -cp "..\ExternalJars\*;" TestRunner
      ```
#### NOTE: If there are more than 10 results there will be instructions displayed on the command line on how to see next and previous results.
 
      
  
