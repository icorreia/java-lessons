package com.icorreia.dp.guice;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.icorreia.dp.guice.modules.AnnotatedPrinterModule;
import com.icorreia.dp.guice.modules.FilePrinterModule;
import com.icorreia.dp.guice.modules.WebPrinterModule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 *
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class GuiceDependencyTests {

    /**
     * A Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(GuiceDependencyTests.class);

    @Test
    public void testDependencyInjection() {
        long start = System.currentTimeMillis();
        Injector webInjector = Guice.createInjector(new WebPrinterModule());
        Printer webPrinter = webInjector.getInstance(Printer.class);
        Injector fileInjector = Guice.createInjector(new FilePrinterModule());
        Printer filePrinter = fileInjector.getInstance(Printer.class);
        long end = System.currentTimeMillis();

        assertEquals("", "WebPrinter", webPrinter.getPrinter());
        assertEquals("", "FilePrinter", filePrinter.getPrinter());

        logger.info("Ran in {}ms.", (end - start));
    }

    @Test
    public void testInjectedField() {
        long start = System.currentTimeMillis();
        InjectedField injected = new InjectedField();
        Injector fileInjector = Guice.createInjector(new FilePrinterModule());
        fileInjector.injectMembers(injected);
        long end = System.currentTimeMillis();

        assertEquals("", "FilePrinter", injected.getPrinter());

        logger.info("Ran in {}ms.", (end - start));
    }

    @Test
    public void testAnnotatedInjectedField() {
        long start = System.currentTimeMillis();
        AnnotatedInjectedField injected = new AnnotatedInjectedField();
        Injector fileInjector = Guice.createInjector(new AnnotatedPrinterModule());
        fileInjector.injectMembers(injected);
        long end = System.currentTimeMillis();

        assertEquals("", "FilePrinter", injected.getFilePrinter());
        assertEquals("", "WebPrinter", injected.getWebPrinter());

        logger.info("Ran in {}ms.", (end - start));
    }
}
