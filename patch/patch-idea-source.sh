#---- Configurations mandatory ------------
#This should be the folder containing the main build.xml file
idea_source_location=/foss/ctap-dev-studio/intellij-community-idea-140.455/
#Give a clean folder to keep the temporary backups. Do not delete till patching is complete
#------- end of configurations ---------

#setting paths

mydir=`pwd`

backup_location=$mydir/backup
mkdir $backup_location

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

echo "clean up"
rm -rf $backup_location

