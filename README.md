#### Check Xcode release and send email

- Read the data.json from xcoderelease [https://xcodereleases.com/data.json]
- parse the above json and export it in csv format
- xcodeVersion() class read the csv file and parse it.
- xcodeVersion() class send the email to mentioned email id in sendEmail.java

#### Build,Complile and run

- `mvn clean install` This will create the jar in target directory under project.
- `java -jar xcoderelease-0.0.1-SNAPSHOT-spring-boot.jar` - This will start the execution and create the results file.
- `/tmp/files/xcodereleasedresults.txt` file shows the result of latest xcode released.
- `/tmp/files/xocdeGMreleasedresults.txt` file show the results of latest XcodeGM released.

#### Enable slack support 
- Create a jenkins job and add that repo.
- add slack plugin and send the results file.