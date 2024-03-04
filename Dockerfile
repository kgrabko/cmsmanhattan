

# For Java 11, try this
# FROM tomcat
#FROM consol/tomcat-7.0
FROM fabric8/tomcat-7

#COPY target/cms-manhattan-one-0.0.5.war /usr/local/tomcat/webapps/ROOT.war

COPY target/cms-manhattan-one-0.0.10.war /opt/apache-tomcat-7.0.90/webapps/cms.war
COPY webmail/hupa-0.0.3.war /opt/apache-tomcat-7.0.90/webapps/webmail.war
##COPY target/cms-manhattan-one-0.0.8.war /opt/apache-tomcat-7.0.90/webapps/ROOT.war
