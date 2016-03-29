<snippet>
  <content><![CDATA[
# ${1:Project Name}
StarFlex Emulator
## Installation
To generate the JAR, run: gradle clean jar
We can find the jar file in /build/libs/riot-core-bridges-starflex-emulator-dev.jar
## Usage
To display the help: java -jar riot-core-bridges-starflex-emulator-dev.jar -help
 -h <arg>   mqtt host (defaults to localhost)
 -help      show this help
 -m <arg>   macId (defaults to F48E729BB)
 -p <arg>   mqtt port (defaults to 1883)
 -r <arg>   number of records (defaults to -1 (Unlimited))
 -s <arg>   number of seconds to run (defaults to 1)
 -t <arg>   starflex type (ex. data, ie, request, response)
 
We can generate data for the following starflex type: ie, data, request and response.
Sample:
java -jar riot-core-bridges-starflex-emulator-dev.jar -t "ie" -h "localhost" -m "BT0001"
]]></content>
  <tabTrigger>readme</tabTrigger>
</snippet>