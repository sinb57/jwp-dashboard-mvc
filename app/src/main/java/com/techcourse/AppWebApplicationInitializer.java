package com.techcourse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletContext;
import nextstep.mvc.DispatcherServlet;
import nextstep.mvc.controller.AnnotationHandlerAdapter;
import nextstep.mvc.controller.AnnotationHandlerMapping;
import nextstep.web.WebApplicationInitializer;

public class AppWebApplicationInitializer implements WebApplicationInitializer {

    private static final Logger log = LoggerFactory.getLogger(AppWebApplicationInitializer.class);

    @Override
    public void onStartup(final ServletContext servletContext) {
        final var dispatcherServlet = new DispatcherServlet();
        addAnnotationHandler(dispatcherServlet);

        final var dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        log.info("Start AppWebApplication Initializer");
    }

    private void addAnnotationHandler(final DispatcherServlet dispatcherServlet) {
        dispatcherServlet.addHandlerMapping(new AnnotationHandlerMapping());
        dispatcherServlet.addHandlerAdapter(new AnnotationHandlerAdapter());
    }
}
