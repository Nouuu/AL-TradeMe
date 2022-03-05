package org.larrieulacoste.noe.al.trademe.configuration;

import org.larrieulacoste.noe.al.trademe.kernel.io.*;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@Dependent
final class IOConfiguration {

    @Produces
    @FileQualifier("")
    Reader reader(InjectionPoint injectionPoint) {
        String path = "repositories/" + injectionPoint.getAnnotated().getAnnotation(FileQualifier.class).value();
        return new FileReader(path);
    }

    @Produces
    @FileQualifier("")
    Writer writer(InjectionPoint injectionPoint) {
        String path = "repositories/" + injectionPoint.getAnnotated().getAnnotation(FileQualifier.class).value();
        return new FileWriter(path);
    }
}
