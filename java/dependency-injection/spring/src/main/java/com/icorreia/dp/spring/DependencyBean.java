package com.icorreia.dp.spring;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * All the classes annotated with @Resource must also have a @Component on their own.
 *
 * @author Ivo Correia (idvcorreia@gmail.com)
 * @since 1.0
 */
@Component("printerBean")
public class DependencyBean {

    private Printer printer;

    @Resource(name="filePrinterDependency")
    private Printer filePrinter;

    @Resource(name="webPrinterDependency")
    private Printer webPrinter;


    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public String getPrinter() {
        return printer.getPrinter();
    }

    public String getFilePrinter() {
        return filePrinter.getPrinter();
    }

    public String getWebPrinter() {
        return webPrinter.getPrinter();
    }



}
