package com.awesomecontrols.quickpopup;

import javax.servlet.annotation.WebServlet;

import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinServletConfiguration;

@WebServlet(urlPatterns = "/*", name = "VaadinFlowLabServlet", asyncSupported = true)
public class VaadinFlowLabServlet extends VaadinServlet {
}