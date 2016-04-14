package com.icorreia;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by ivo.correia on 24/03/2016.
 */
public class ClassLoaderTester {

    /**
     * A Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private static final String jarFilePath = "java/class-loading/version1/target/version1-1.0-SNAPSHOT.jar";

    @Test
    public void overrideParentClass() throws Exception{
        JarLoader jarLoader = new JarLoader(Thread.currentThread().getContextClassLoader(), jarFilePath, true);

        BasePrinter filePrinterV0 = new FilePrinter();
        BasePrinter filePrinter = (BasePrinter) (jarLoader.loadClass("com.icorreia.FilePrinter")).newInstance();
        BasePrinter webPrinter = (BasePrinter) (jarLoader.loadClass("com.icorreia.WebPrinter")).newInstance();

        assertEquals("Package class should not be overriden from JAR implementation.", "FilePrinter-v0", filePrinterV0.getVersion());
        assertEquals("Package class should be overriden from JAR implementation.", "FilePrinter-v1", filePrinter.getVersion());
        assertEquals("Class from JAR should be added to the classpath.", "WebPrinter", webPrinter.getVersion());
    }

    @Test
    public void doNotOverrideParentClass() throws Exception{
        JarLoader jarLoader = new JarLoader(Thread.currentThread().getContextClassLoader(), jarFilePath, false);

        BasePrinter filePrinterV0 = new FilePrinter();
        BasePrinter filePrinter = (BasePrinter) (jarLoader.loadClass("com.icorreia.FilePrinter")).newInstance();
        BasePrinter webPrinter = (BasePrinter) (jarLoader.loadClass("com.icorreia.WebPrinter")).newInstance();

        assertEquals("Package class should not be overriden from JAR implementation.", "FilePrinter-v0", filePrinterV0.getVersion());
        assertEquals("Package class should not be overriden from JAR implementation.", "FilePrinter-v0", filePrinter.getVersion());
        assertEquals("Class from JAR should be added to the classpath.", "WebPrinter", webPrinter.getVersion());
    }
}
