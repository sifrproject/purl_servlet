package fr.lirmm.bioportal;


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
    private String purlUrl = "http://purl.lirmm.fr";

    // redirect GET to POST
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * Get the URL request path and redirect to the class in the BioPortal UI 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ontologyAcronym = "";
        String urlString = request.getPathInfo();

        // Extract ontology acronym and concept ID from the path (e.g.: /ontology/STY/T071)
        Pattern pattern = Pattern.compile("\\/ontology\\/(.*?)(?:\\/(.*))");
        Matcher matcher = pattern.matcher(urlString);
        if (matcher.find())
        {
            ontologyAcronym = matcher.group(1);
            //conceptId = matcher.group(2);
        } else {
            ontologyAcronym = "";
        }

        // Build the URL to the class in the bioportal UI like this: 
        // http://bioportal.lirmm.fr/ontologies/$ONT_ACRO/?p=classes&conceptid=$ENCODED("http://purl.lirmm.fr" + conceptPath)
        String uiLink = uiURL + "/ontologies/" + ontologyAcronym + "?p=classes&conceptid=" + URLEncoder.encode(purlUrl + urlString, "UTF-8");

        response.sendRedirect(uiLink);
    }

}
