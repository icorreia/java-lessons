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
        JarLoader jarLoader = new JarLoader();

        Class<?> clazz = jarLoader.loadJarV1();
        Constructor<?> ctor = clazz.getConstructor(Printer.class);
        Printer printer = (Printer) ctor.newInstance();

        logger.info("The version is: {}", printer.getVersion());
    }
}
