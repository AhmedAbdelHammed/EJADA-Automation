# EJADA-Automation

This code can be used to convert XML to JSON and extract specific values for EJADA XMLResponseData.xml  

Tools Needed
---------------------------------------------
- Java version above 17  
- Maven

Project Structure
---------------------------------------------  
**utils** package contains the below classes:  
- **JsonToXML** class have all these general utilities:  
  - read XML file and convert it to JSON  
  - extract Integer value from a key in the JSON  
  - extract List from a key in the JSON  
- **ExtractValues** class have the functions needed to extract the following values:  
  - DecisionFlowId  
  - SCORE value  
  
**resources** folder that have the **XMLResponseData.xml** file  
  
**Main** class that call the ExtractValues functions  

## How to execute

From the terminal or cmd execute the below command  
```
mvn exec:java
```
**DecisionFlowId** and **SCORE** values will be printed on the console  
 

