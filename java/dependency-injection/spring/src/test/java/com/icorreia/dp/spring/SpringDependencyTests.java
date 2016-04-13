package com.icorreia.dp.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

/**
 *
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class SpringDependencyTests {
    
    @Test
    public void testDependencyInjection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");

        DependencyBean webPrinter = (DependencyBean) context.getBean("webPrinterBean");
        DependencyBean filePrinter = (DependencyBean) context.getBean("filePrinterBean");

        assertEquals("", "WebPrinter", webPrinter.getPrinter());
        assertEquals("", "FilePrinter", filePrinter.getPrinter());
    }
}
