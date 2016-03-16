package com.icorreia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 *
 * This class loader gives privilege
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class JarLoader extends ClassLoader {

    /**
     * Cache and verify if class is defined already.
     */
    private HashMap<String, Class> classes = new HashMap<>();

    /**
     * A Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(JarLoader.class);

    private final boolean priorityToLocalClasses;

    private byte[] jar;

    public JarLoader(final ClassLoader classLoader, final String jarPath, final boolean priorityToLocalClasses) throws IOException {
        super(classLoader);
        this.classes = new HashMap<>();
        this.priorityToLocalClasses = priorityToLocalClasses;
        this.jar = Files.readAllBytes(Paths.get(jarPath));
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException{
        return findClass(name);
    }

    /**
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException{

        if (priorityToLocalClasses) {
            Class<?> clazz = loadLocalClass(name);
            if (clazz != null) {
                return clazz;
            }
        }

        try {
            return Class.forName(name);
        }
        catch (ClassNotFoundException nfe) {
            try {
                return this.getParent().loadClass(name);
            }
            catch (ClassNotFoundException nfe2) {
                try {
                    return ClassLoader.getSystemClassLoader().loadClass(name);
                } catch (ClassNotFoundException nfe3) {
                    return loadLocalClass(name);
                }
            }
        }
    }

    public Class<?> loadLocalClass(String name) {
        if (this.classes.containsKey(name)) {
            return this.classes.get(name);
        }
        try {
            final String className = name.concat(".class");
            final ByteArrayInputStream bais = new ByteArrayInputStream(this.jar);
            final JarInputStream in = new JarInputStream(bais);
            JarEntry next;
            while ((next = in.getNextJarEntry()) != null) {
                // We are loading a single class.
                if (next.getName().replaceAll("/", ".").equals(className)) {
                    final ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        byte[] buffer = new byte[2048];
                        int read;
                        while (in.available() > 0) {
                            read = in.read(buffer, 0, buffer.length);
                            if (read < 0) {
                                break;
                            }
                            out.write(buffer, 0, read);
                        }
                        buffer = out.toByteArray();
                        final Class<?> aClass = this.defineClass(name, buffer, 0, buffer.length);
                        this.classes.put(name, aClass);
                        return aClass;
                    }
                    finally {
                        out.close();
                        bais.close();
                    }
                }
            }
        }
        catch (IOException e) {
            logger.error("Could not load class '{}': ", name, e);
        }

        return null;
    }

}