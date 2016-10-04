package org.sifrproject;


import java.io.IOException;
import java.net.URLEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * A simple PURL servlet to resolve purl.lirmm.fr URIs
 * It redirects http://localhost:$TOMCAT_PORT/purl/ontology/$ONTO_ACRONYM/$CONCEPT_ID
 * to http://bioportal.lirmm.fr/ontologies/$ONTO_ACRONYM?p=classes&conceptid=$CONCEPT_ID
 * 
 * @authors Vincent Emonet
 */
public class PurlServlet extends HttpServlet {

    private String uiURL = "http://bioportal.lirmm.fr";

    // redirect GET to POST
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    // POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ontologyAcronym = "";
        String conceptId = "";
        String urlString = request.getPathInfo();

        // Extract ontology acronym and concept ID from the path (e.g.: /ontology/STY/T071)
        Pattern pattern = Pattern.compile("\\/ontology\\/(.*?)(?:\\/(.*))");
        Matcher matcher = pattern.matcher(request.getPathInfo());
        if (matcher.find())
        {
            ontologyAcronym = matcher.group(1);
            conceptId = matcher.group(2);
        } else {
            ontologyAcronym = "";
        }

        // Build the URL to the ontology and concept in the bioportal UI
        String uiLink = uiURL + "/ontologies/" + ontologyAcronym + "?p=classes&conceptid=" + 
                URLEncoder.encode(request.getRequestURL().toString() , "UTF-8");

        response.sendRedirect(uiLink);
    }

}
