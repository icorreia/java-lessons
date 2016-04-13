package com.icorreia.dp.spring;

/**
 *
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
public class DependencyBean {

    private Printer printer;

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public String getPrinter() {
        return printer.getPrinter();
    }

}
