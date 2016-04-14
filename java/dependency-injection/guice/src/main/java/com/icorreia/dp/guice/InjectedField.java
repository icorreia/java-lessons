package com.icorreia.dp.guice;

import com.google.inject.Inject;

/**
 *
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class InjectedField {

    @Inject
    private Printer printer;

    public String getPrinter() {
        return printer.getPrinter();
    }

}
