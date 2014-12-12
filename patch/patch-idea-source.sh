#---- Configurations mandatory ------------
#This should be the folder containing the main build.xml file
idea_source_location=/home/hsenid/Desktop/code-sample-app/intellij/intellij-community
#Give a clean folder to keep the temporary backups. Do not delete till patching is complete
backup_location=/home/hsenid/Desktop/code-sample-app/backup
#path to ant bin
ant_bin=/home/hsenid/Desktop/build-reqs/apache-ant-1.9.4/bin
#path to java home, not bin. should be 1.6
java_home=/home/hsenid/Desktop/build-reqs/jdk1.6.0_45
#------- end of configurations ---------

#setting paths
export JAVA_HOME=$java_home
export PATH=$JAVA_HOME/bin:$ant_bin:$PATH

java -version
mydir=`pwd`
#create backup directories
cd $backup_location
mkdir community-resources-src platform-icons-src
cp $idea_source_location/community-resources/src/*.png community-resources-src/
cp $idea_source_location/community-resources/src/idea/IdeaApplicationInfo.xml .
cp $idea_source_location/platform/icons/src/*.png platform-icons-src/

#patch the source
cd $mydir
cp community-resources-src/*.png $idea_source_location/community-resources/src/
cp platform-icons-src/*.png $idea_source_location/platform/icons/src/
cp IdeaApplicationInfo.xml $idea_source_location/community-resources/src/idea/

#build the source
cd $idea_source_location
ant
echo "Build complete.Please see the out/artifacts for the binaries"

#revert the source
cd $backup_location
cp community-resources-src/*.png $idea_source_location/community-resources/src/
cp platform-icons-src/*.png $idea_source_location/platform/icons/src/
cp IdeaApplicationInfo.xml $idea_source_location/community-resources/src/idea/
cd $mydir
echo "Source reverting complete"



