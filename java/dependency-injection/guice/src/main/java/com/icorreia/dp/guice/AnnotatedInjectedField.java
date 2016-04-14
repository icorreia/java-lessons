package com.icorreia.dp.guice;

import com.google.inject.Inject;
import com.icorreia.dp.guice.annotations.File;
import com.icorreia.dp.guice.annotations.Web;

/**
 *
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class AnnotatedInjectedField {

    @Inject
    @Web
    private Printer webPrinter;

    @Inject
    @File
    private Printer filePrinter;

    public String getWebPrinter() {
        return webPrinter.getPrinter();
    }

    public String getFilePrinter() {
        return filePrinter.getPrinter();
    }

}
