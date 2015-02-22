java -Dcom.sun.management.jmxremote \
     -Dcom.sun.management.jmxremote.port=1617 \
     -Dcom.sun.management.jmxremote.authenticate=false \
     -Dcom.sun.management.jmxremote.ssl=false \
     -Dhms.ctap.simulator.Main \
     -jar /home/oshan/git-projects/code-fest/ctap-idea-plugin/ctap-simulator-stand-alone/target/ctap-simulator-1.0-SNAPSHOT.jar
#java -jar /home/oshan/git-projects/code-fest/ctap-idea-plugin/ctap-simulator-stand-alone/target/ctap-simulator-1.0-SNAPSHOT.jar java -Dhms.ctap.simulator.Main
#java -jar /home/oshan/git-projects/code-fest/ctap-idea-plugin/ctap-simulator-stand-alone/target/ctap-simulator-1.0-SNAPSHOT.jar -Dcom.sun.management.jmxremote.port=9999

