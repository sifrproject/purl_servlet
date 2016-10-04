# BioPortal pURL Servlet
A simple servlet to resolve http://purl.lirmm.fr URIs

* In the case of http://purl.lirmm.fr it redirects `http://bioportal.lirmm.fr:$TOMCAT_PORT/purl/ontology/$ONTO_ACRONYM/$CONCEPT_ID` to

`http://bioportal.lirmm.fr/ontologies/$ONTO_ACRONYM?p=classes&conceptid=$CONCEPT_PURL`

* You need to redirect http://purl.lirmm.fr to http://localhost:$TOMCAT_PORT to have it deferencing the purl URL.

### Update

* Clone the repository 
* Use maven to package the war: `mvn clean package`


* Then to update it on bioportal.lirmm.fr tomcat go to http://services.bioportal.lirmm.fr, remove the previous purl servlet and deploy your purl.war