/* (C)2022 */
package io.validate.context.app;

import io.validate.api.internal.DefaultArgumentProvider;
import io.validate.api.internal.DefaultPreconditionProvider;
import io.validate.api.internal.DefaultValidationProvider;
import io.validate.api.internal.ResourceProvider;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Context {
    private final Map<ApplicationPath, ArrayList<ValidationConfig>> context =
            new ConcurrentHashMap<>();
    private final Map<String, ApplicationPath> auxiliaryContext = new ConcurrentHashMap<>();
    private final Map<String, List<ApplicationPath>> methodsToBeInterceptedContext =
            new ConcurrentHashMap<>();
    private final List<Class> classesToBeIntercepted =
            Collections.synchronizedList(new ArrayList<>());
    private final Map<String, Class> validationMap = new ConcurrentHashMap<>();
    private final Map<String, Class> preconditionMap = new ConcurrentHashMap<>();
    private final Map<String, Class> argumentProviderMap = new ConcurrentHashMap<>();
    private static Context instance = null;
    private final ResourceProvider currentPreconditionProvider;
    private final ResourceProvider currentValidationProvider;
    private final ResourceProvider currentArgumentProvider;

    private Context() {
        this.currentPreconditionProvider = getCurrentPreconditionProvider();
        this.currentValidationProvider = getCurrentValidationProvider();
        this.currentArgumentProvider = getCurrentArgumentProvider();
    }

    public ResourceProvider getCurrentArgumentProvider() {
        if (currentArgumentProvider == null)
            return new DefaultArgumentProvider(); // replace it with SPI calls for spring and other
        // object providers
        return currentArgumentProvider;
    }

    public ResourceProvider getCurrentValidationProvider() {
        if (currentValidationProvider == null)
            return new DefaultValidationProvider(); // replace it with SPI calls for spring and
        // other object providers
        return currentValidationProvider;
    }

    public ResourceProvider getCurrentPreconditionProvider() {
        if (currentPreconditionProvider == null)
            return new DefaultPreconditionProvider(); // replace it with SPI calls for spring and
        // other object providers
        return currentPreconditionProvider;
    }

    public void addConfig(String applicationPath, String validationConfig) {}

    public void addConfigs(String applicationPath, ArrayList<String> validatorsName) {}

    public void addValidation(String validationName, Class validationClass) {}

    public void addPrecondition(String preconditionName, Class preconditionClass) {}

    public void addArgumentProvider(String argumentProviderName, Class argumentProviderClass) {}

    public Class getArgumentProvider(String argumentProviderName) {
        return argumentProviderMap.get(argumentProviderName);
    }

    public Class getValidationClass(String validationName) {
        return validationMap.get(validationName);
    }

    public Class getPrecondition(String preconditionName) {
        return preconditionMap.get(preconditionName);
    }

    public List<ValidationConfig> getValidationConfigs(String applicationPath) {
        ApplicationPath applicationPathObj = auxiliaryContext.get(applicationPath);
        return context.get(applicationPathObj);
    }

    public List<ApplicationPath> getApplicationPathsForClassName(String className) {
        return methodsToBeInterceptedContext.get(className);
    }

    public List<Method> getMethodsToBeIntercepted(String className) {
        return getApplicationPathsForClassName(className).stream()
                .map(applicationPath -> applicationPath.getApplicationMethod())
                .collect(Collectors.toList());
    }

    public List<Class> getClassesToBeIntercepted() {
        return classesToBeIntercepted;
    }

    public static Context getInstance() {
        if (instance == null) instance = initContext();
        return instance;
    }

    private static Context initContext() {
        Context context = new Context();
        return context;
    }

    public List<ValidationConfig> getValidationConfigsForMethod(String methodName) {
        ApplicationPath applicationPath =
                ApplicationPath.createApplicationPathForMethodName(methodName);
        return getValidationConfigs(applicationPath.getConfigApplicationPath());
    }
}
