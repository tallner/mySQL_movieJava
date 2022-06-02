# Getting started

## Set up the database for Windows
To create the database run [this script](https://github.com/tallner/mySQL_movieJava/blob/main/mysql_scripts/createDefaultDB.bat) in the cmd prompt.  
Before you run it you need to have a mySQL database running on your local machine or you will get a connection error.   
  
Make sure to modify following in the script:  
* mySQLpath - this is the path to the mysql.exe file in your system
* myPW - the password to your database
* myUS - your username to your database

[ER Diagram](https://github.com/tallner/mySQL_movieJava/blob/main/mysql_scripts/ERdiagram.drawio.pdf)  

If you want to run other SQL query files than [sqlfiles](https://github.com/tallner/mySQL_movieJava/tree/main/mysql_scripts/sqlfiles) then you also need to change the filepath, mySQLfilePath, and filenames in [the script](https://github.com/tallner/mySQL_movieJava/blob/main/mysql_scripts/createDefaultDB.bat)


## Run the JAVA application
* Start the application in, for example, Eclipse  
* In Eclipse, Run As Java Application  
* You will get a simple UI in the Console  
* Select 99 to run a pre-made story  
* Select 1-25 to test each method separate - NOTE! SPACE INPUTS NOT TAKEN CARE OF!
* Select 100 to exit
