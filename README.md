# purl_servlet
A simple servlet to resolve purl.lirmm.fr URIs

It redirects http://localhost:$TOMCAT_PORT/purl/ontology/$ONTO_ACRONYM/$CONCEPT_ID to http://bioportal.lirmm.fr/ontologies/$ONTO_ACRONYM?p=classes&conceptid=$CONCEPT_ID
