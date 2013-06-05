package com.att.research.openstack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class VoiceCommandServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/tmp/log", true)));
            if (request.getParameter("RecordingUrl") != null) {
                out.println(request.getParameter("RecordingUrl"));
            } else {
                out.println("RecordingUrl parameter not found!");
            }
            out.close();
        } catch (IOException e) {
            //oh noes!
        }
       
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>RESPONSE GENERATED</h1>");
    }
}
