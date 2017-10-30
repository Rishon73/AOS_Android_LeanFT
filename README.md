# AOS_Android_UFTPro

UFTPro (LeanFT) script with TestNG framework.

For parallel executions use the @Test annotaion:

@Test (threadPoolSize = 3, invocationCount = 1) invocationCount dictates the number of devices to run the script.


Important Note:
---------------
This project requires adding **MCUtils.jar** file into the local Maven repository.
The project includes the jar file under the _**MCUtils_jar**_ folder but the most updated version of it will be found here:
https://github.com/Rishon73/MCUtils.git

This git repository has a ready to use jar file under _MCUtils/out/artifacts/MCUtils_jar/ folder_.
To include it in your local maven repository, run this command in terminal:

_mvn install:install-file -Dfile=<location of MCUtils.jar> -DgroupId=com.mf -DartifactId=MCUtilities -Dversion=4.0.0 -Dpackaging=jar_
