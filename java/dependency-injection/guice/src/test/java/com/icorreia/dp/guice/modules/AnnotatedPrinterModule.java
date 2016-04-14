package com.icorreia.dp.guice.modules;

import com.google.inject.AbstractModule;
import com.icorreia.dp.guice.FilePrinter;
import com.icorreia.dp.guice.Printer;
import com.icorreia.dp.guice.WebPrinter;
import com.icorreia.dp.guice.annotations.File;
import com.icorreia.dp.guice.annotations.Web;

/**
 *
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class AnnotatedPrinterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Printer.class).annotatedWith(File.class).to(FilePrinter.class);
        bind(Printer.class).annotatedWith(Web.class).to(WebPrinter.class);
    }
}
