/* (C)2022 */
package io.validate.context.app;

import java.lang.reflect.Method;

public class ApplicationPath {
    private String configApplicationPath;
    private Package applicationPackage;
    private Class applicationClass;
    private Method applicationMethod;

    private ApplicationPath() {}

    public ApplicationPath(String configApplicationPath) {
        this.configApplicationPath = configApplicationPath;
    }

    public static ApplicationPath createApplicationPathForMethodName(String methodName) {
        return new ApplicationPath();
    }

    public String getConfigApplicationPath() {
        return configApplicationPath;
    }

    public Package gePackage() {
        if (applicationPackage == null) setApplicationPackage();
        return applicationPackage;
    }

    private void setApplicationPackage() {}

    public Class getApplicationClass() {
        if (applicationClass == null) setApplicationClass();
        return applicationClass;
    }

    private void setApplicationClass() {}

    public Method getApplicationMethod() {
        if (applicationMethod == null) setApplicationMethod();
        return applicationMethod;
    }

    private void setApplicationMethod() {}
}
