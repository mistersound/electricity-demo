# Arrowhead SoS example

This is the implementation of an Electricity management system for Arrowhead, developed by:
Junior Dongo\
Distributed, Embedded and Intelligent Systems, Department of Computer Science, Aalborg University

It is used to manage electricity pricing. Only price category is consider at the moment.

### Requirements

The project has the following dependencies:
* JRE/JDK 11 [Download from here](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
* Maven 3.5+ [Download from here](http://maven.apache.org/download.cgi) | [Install guide](https://www.baeldung.com/install-maven-on-windows-linux-mac)
* GitHub Packages [Configuring Maven for GitHub Packages](https://help.github.com/en/packages/using-github-packages-with-your-projects-ecosystem/configuring-apache-maven-for-use-with-github-packages)

### Project structure

This is a multi-module maven project relying on the [parent `pom.xml`](https://github.com/mistersound/electricity-demo/blob/master/pom.xml) which lists all the modules and common dependencies.

##### Modules:

* **electricity-provider**: registering the services for creating price category and get the price categories into the Service Registry and running a web server where the services are available.

* **electricity-consumer**: initiating an orchestration request and consume the service of getting the different categories from the electricity provider. 

* **electricity-operator**: registering an orchestration request and consume the service of creating the different categories from the electricity provider.






