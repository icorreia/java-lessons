package com.icorreia.dp.spring;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

/**
 *
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class SpringDependencyTests {

    /**
     * A Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(FilePrinter.class);

    @Test
    public void testXmlDependencyInjection() {
        long start = System.currentTimeMillis();
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");

        DependencyBean webPrinter = (DependencyBean) context.getBean("webPrinterBean");
        DependencyBean filePrinter = (DependencyBean) context.getBean("filePrinterBean");
        long end = System.currentTimeMillis();

        assertEquals("", "WebPrinter", webPrinter.getPrinter());
        assertEquals("", "FilePrinter", filePrinter.getPrinter());

        logger.info("Ran in {}ms.", (end - start));
    }

    @Test
    public void testAnnotationDependencyInjection() {
        long start = System.currentTimeMillis();
        // Here is the class containing the @Configuration and @ComponentScan annotations.
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(InjectionConfig.class);

        //Gets the root bean, containing a @Component annotation.
        DependencyBean application = (DependencyBean)context.getBean("printerBean");
        long end = System.currentTimeMillis();

        assertEquals("", "WebPrinter", application.getWebPrinter());
        assertEquals("", "FilePrinter", application.getFilePrinter());

        logger.info("Ran in {}ms.", (end - start));
    }
}
