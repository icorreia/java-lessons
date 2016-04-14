package com.icorreia.dp.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
@Component("filePrinterDependency")
public class FilePrinter implements Printer {

    /**
     * A Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(FilePrinter.class);

    public String getPrinter() {
        return "FilePrinter";
    }
}
