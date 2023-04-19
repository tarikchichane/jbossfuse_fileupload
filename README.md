# jbossfuse_fileupload

in this tutorial we will create a jboss fuse osgi that will serve a webservice

to deploy the bundle on jboss fuse you will need to install the project

in a command line you will point to the project directory

you will launch mvn clean install 

after the project is build and installed you will need to install it on the jboss fuse platform (I am using JBoss Fuse fuse-karaf-7.11.1.fuse-7_11_1-00013-redhat-00003)

you start fuse 

and laucn the command

osgi:install -s wrap:mvn:org.bam/camelfileWebService/0.0.1-SNAPSHOT

the project will give you 3 method on the webservice call to 

1- http://localhost:8181/cxf/fileservice/fileset/download/file3.txt to download file3.txt or any file that you will send as parameter with the HTTP GET method.
2- http://localhost:8181/cxf/fileservice/fileset/cksum/file3.txt to retrieve the cksum of the file3
3-http://localhost:8181/cxf/fileservice/fileset/listFile to list the file on a directory the directory is defined as a constant on the class as 

static final directory

hope you enjoy it
