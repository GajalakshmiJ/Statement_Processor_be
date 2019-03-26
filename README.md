# Statement_Processor_be
It is developed as dynamic web project in java with JDK1.8 and Apache Tomcat 7.0



## Execution
Running on server the csvProcessing.jsp file, displays the failed records reference and description of records.csv file 
located in WEB-INF/res folder

Running on server the xmlProcessing.jsp file, displays the failed records reference and description of records.xml file 
located in WEB-INF/res folder


## Description
failed records are the records those have duplicate reference and endbalance mismatch. The failed records identification is 
done by CustomerStatementProcessor class.

CSVParser and XMLParser classes parsing the CSV and XML file respectively and passes the string array to CustomerStatementProcessor class
for failed record identification.

