package com.epam.esm.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) {
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
    applicationContext.scan("com.epam.esm");
    servletContext.addListener(new ContextLoaderListener(applicationContext));
    DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
    dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    ServletRegistration.Dynamic appServlet =
        servletContext.addServlet("dispatcher", dispatcherServlet);
    appServlet.setLoadOnStartup(1);
    appServlet.addMapping("/");
  }
}