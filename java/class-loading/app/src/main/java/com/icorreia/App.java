package com.icorreia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

/**
 *
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class App {

    /**
     * A Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * Entry point for application.
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{
        /**
         * The loading class loader can be either:
         *  - Thread.currentThread().getContextClassLoader()
         *  - [CLASS_NAME].class.getClassLoader()
         *
         * If the value is 'null' and priorityToLocalClasses=false, it will only be able to return
         * twice the v0 for FilePrinter and will raise an exception for WebPrinter.
         */
        JarLoader jarLoader = new JarLoader(null, "java/class-loading/version1/target/version1-1.0-SNAPSHOT.jar", false);

        AppPrinter filePrinterV0 = new FilePrinter();
        logger.info("Printer says: {}", filePrinterV0.getVersion());

        Class<?> clazz = jarLoader.loadClass("com.icorreia.FilePrinter");
        AppPrinter filePrinter = (AppPrinter) clazz.newInstance();
        logger.info("Printer says: {}", filePrinter.getVersion());

        clazz = jarLoader.loadClass("com.icorreia.WebPrinter");
        AppPrinter webPrinter = (AppPrinter) clazz.newInstance();
        logger.info("Printer says: {}", webPrinter.getVersion());
    }
}
