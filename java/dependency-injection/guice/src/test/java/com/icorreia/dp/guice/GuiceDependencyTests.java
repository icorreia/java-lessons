package com.icorreia.dp.guice;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.icorreia.dp.guice.modules.FilePrinterModule;
import com.icorreia.dp.guice.modules.WebPrinterModule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class GuiceDependencyTests {

    @Test
    public void testDependencyInjection() {
        Injector webInjector = Guice.createInjector(new WebPrinterModule());
        Printer webPrinter = webInjector.getInstance(Printer.class);
        Injector fileInjector = Guice.createInjector(new FilePrinterModule());
        Printer filePrinter = fileInjector.getInstance(Printer.class);

        assertEquals("", "WebPrinter", webPrinter.getPrinter());
        assertEquals("", "FilePrinter", filePrinter.getPrinter());
    }

    @Test
    public void testInjectedField() {
        InjectedField injected = new InjectedField();
        Injector fileInjector = Guice.createInjector(new FilePrinterModule());
        fileInjector.injectMembers(injected);

        assertEquals("", "FilePrinter", injected.getPrinter());
    }
}
