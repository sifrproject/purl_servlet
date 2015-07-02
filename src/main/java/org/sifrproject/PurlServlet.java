package org.sifrproject;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


/**
 * A simple PURL servlet to resolve purl.lirmm.fr URIs
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

        // Extract ontology acronym and concept ID from the path (e.g.: /ontology/STY/T071)
        Pattern pattern = Pattern.compile("\\/ontology\\/(.*?)(?:\\/(.*))");
        Matcher matcher = pattern.matcher(request.getPathInfo().toString());
        if (matcher.find())
        {
            ontologyAcronym = matcher.group(1);
            conceptId = matcher.group(2);
        } else {
            ontologyAcronym = "";
        }

        // Build the URL to the ontology and concept in the bioportal UI
        String uiLink = uiURL + "/ontologies/" + ontologyAcronym + "?p=classes&conceptid=" + conceptId;

        response.sendRedirect(uiLink);
    }

}
