#!/bin/bash

# IDEA_SOURCE_LOCATION=<idea source location> ./package_all.sh


if [ ! -z  "$IDEA_SOURCE_LOCATION" ]
then 
	echo "Idea community source location set to $IDEA_SOURCE_LOCATION"
else 
	echo ""
	echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
	echo "Error !!!, set the IDEA_SOURCE_LOCATION Ex: "
	echo "IDEA_SOURCE_LOCATION=/foss/tap-dev-studio/intellij-community-idea-140.455/ ./package_all.sh"
	echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
	echo ""
	exit 2
fi

if [ ! -z   "$JAVA_HOME" ]
then
       	echo "Your java home is set to $JAVA_HOME"
	echo `java -version`
else
	echo ""
        echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        echo "Error !!!, set the JAVA_HOME"
	echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
	echo ""
        exit 2
fi

if [ ! -z   "$ANT_HOME" ]
then
       	echo "Your ant home is set to $ANT_HOME"
	echo `ant -version`
else
	echo ""
        echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        echo "Error !!!, set the ANT_HOME"
	echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
	echo ""
        exit 2
fi

CUR_DIR=`pwd`
BACKUP_LOCATION=$CUR_DIR/backup
mkdir $BACKUP_LOCATION

cd $BACKUP_LOCATION

mkdir community-resources-src platform-icons-src

cp $IDEA_SOURCE_LOCATION/community-resources/src/*.png community-resources-src/
cp $IDEA_SOURCE_LOCATION/community-resources/src/idea/IdeaApplicationInfo.xml .
cp $IDEA_SOURCE_LOCATION/platform/icons/src/*.png platform-icons-src/

#Patch the source
cd $CUR_DIR
cp community-resources-src/*.png $IDEA_SOURCE_LOCATION/community-resources/src/
cp platform-icons-src/*.png $IDEA_SOURCE_LOCATION/platform/icons/src/
cp IdeaApplicationInfo.xml $IDEA_SOURCE_LOCATION/community-resources/src/idea/

#Build the source
cd $IDEA_SOURCE_LOCATION
echo "============= Starting build of patched idea source ==========================="
ant
echo "============= Build complete.Please see the $IDEA_SOURCE_LOCATION/out/artifacts for the binaries=================="

#revert the source
cd $BACKUP_LOCATION
cp community-resources-src/*.png $IDEA_SOURCE_LOCATION/community-resources/src/
cp platform-icons-src/*.png $IDEA_SOURCE_LOCATION/platform/icons/src/
cp IdeaApplicationInfo.xml $IDEA_SOURCE_LOCATION/community-resources/src/idea/
cd $CUR_DIR

echo "Source reverting complete"

# Cleanning the backups
rm -rf $BACKUP_LOCATION
echo "Cleanning the temporary back up directories, complted."

#manual repack instructions
echo "______________________________________________________________________________"
echo "----------------- VERY IMPORTANT !!!!!!!!!!!!!!!!!!!!!!! ---------------------"
echo ""
echo "Go to $IDEA_SOURCE_LOCATION/out/artifacts and extract the distribution compressed file for linux. Go to bin folder and rename following files"
echo "idea.vmoptions ----> devstudio.vmoptions"
echo "idea64.vmoptions ----> devstudio64.vmoptions"
echo "Compress the archive again before distributing. This has to be done for Linux only."
echo "This is a workaround for a bug in idea community edition source code"
echo ""
