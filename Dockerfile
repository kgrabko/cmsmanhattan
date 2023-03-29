

# For Java 11, try this
# FROM tomcat
FROM fabric8/tomcat-7

#COPY target/cms-manhattan-one-0.0.5.war /usr/local/tomcat/webapps/ROOT.war

COPY target/cms-manhattan-one-0.0.6.war /opt/apache-tomcat-7.0.90/webapps/cms.war
