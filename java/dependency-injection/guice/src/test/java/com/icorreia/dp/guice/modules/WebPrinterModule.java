package com.icorreia.dp.guice.modules;

import com.google.inject.AbstractModule;
import com.icorreia.dp.guice.Printer;
import com.icorreia.dp.guice.WebPrinter;

/**
 *
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class WebPrinterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Printer.class).to(WebPrinter.class);
    }
}
