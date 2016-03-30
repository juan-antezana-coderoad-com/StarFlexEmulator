<snippet>
  <content>
# StarFlex Emulator

## Installation
To generate the JAR, run: gradle clean jar
We can find the jar file in /build/libs/riot-core-bridges-starflex-emulator-dev.jar
## Usage
To display the help: java -jar riot-core-bridges-starflex-emulator-dev.jar -help

 -h <arg>       mqtt host (defaults to localhost)
 -help          show this help
 -m <arg>       macId (defaults to F48E729BB)
 -maxd <arg>    max number of tag read data messsage (defaults to 1000) -> only works for data starflex type
 -nd <arg>      number of tag read data messsage (defaults to 500) -> only works for data starflex type
 -nr <arg>      number of records (defaults to -1 (Unlimited))
 -ns <arg>      number of seconds for the delay (defaults to 1)
 -p <arg>       mqtt port (defaults to 1883)
 -prefd <arg>   prefix for the data (defaults to TB) -> only works for data starflex type
 -t <arg>       starflex type (ex. data, ie, request, response)
 
We can generate data for the following starflex type: ie, data, request and response.
Sample:
java -jar riot-core-bridges-starflex-emulator-dev.jar -t "ie" -h "localhost" -m "BT0001"
  </content>
  <tabTrigger>readme</tabTrigger>
</snippet>
