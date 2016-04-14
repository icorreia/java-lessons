package com.icorreia.dp.guice.modules;

import com.google.inject.AbstractModule;
import com.icorreia.dp.guice.FilePrinter;
import com.icorreia.dp.guice.Printer;

/**
 *
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class FilePrinterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Printer.class).to(FilePrinter.class);
    }
}
