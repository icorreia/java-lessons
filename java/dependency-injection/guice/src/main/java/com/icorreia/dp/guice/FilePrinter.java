package com.icorreia.dp.guice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class FilePrinter implements Printer {

    /**
     * A Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(FilePrinter.class);

    public String getPrinter() {
        return "FilePrinter";
    }
}
