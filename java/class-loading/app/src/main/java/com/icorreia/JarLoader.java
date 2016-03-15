package com.icorreia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * Initial implementation based on http://kalanir.blogspot.com/2010/01/how-to-write-custom-class-loader-to.html.
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class JarLoader extends ClassLoader {
    /**
     * Path to version 1 of .jar file to load.
     */
    private String jarFileV1 = "java/class-loading/version1/target/version1-1.0-SNAPSHOT.jar";

    /**
     * Path to version 2 of .jar file to load.
     */
    private String jarFileV2 = "java/class-loading/version2/target/version2-1.0-SNAPSHOT.jar";

    /**
     * Cache and verify if class is defined already.
     */
    private HashMap<String, Class> classes = new HashMap<>();

    /**
     * A Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(JarLoader.class);

    public JarLoader() {
        super(JarLoader.class.getClassLoader()); //calls the parent class loader's constructor
        logger.info("Working directory is '{}'.", System.getProperty("user.dir"));
    }


    public Class loadJarV1() throws ClassNotFoundException {
        return findClass("Printer", jarFileV1);
    }

    public Class loadJarV2() throws ClassNotFoundException {
        return findClass("Printer", jarFileV2);
    }

    public Class findClass(String className, String pathToJar) throws ClassNotFoundException {
        byte classByte[];
        Class result = classes.get(className);

        if (result != null) {
            logger.info("Class '{}' already lodaded.", className);
            return result;
        }

        /* In the context of the present exercise, this call should not be successful,
         * as we are trying to load a class from a .jar file which is not yet present
         * in the main class loader.
         */
        try {
            return findSystemClass(className);
        } catch (Exception e) {
            logger.info("Unsuccessfully tried to find class '{}' in system.", className);
        }

        try {
            JarFile jar = new JarFile(pathToJar);
            logger.info("Successfully loaded .jar file.");
            JarEntry entry = jar.getJarEntry(className + ".class");
            InputStream is = jar.getInputStream(entry);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            int nextValue = is.read();
            while (-1 != nextValue) {
                byteStream.write(nextValue);
                nextValue = is.read();
            }

            classByte = byteStream.toByteArray();
            result = defineClass(className, classByte, 0, classByte.length, null);
            classes.put(className, result);
            return result;
        } catch (Exception e) {
            logger.info("Could not load class '{}' from .jar file: ", className, e);
            return null;
        }
    }

}