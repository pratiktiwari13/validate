/* (C)2023 */
package io.validate.preprocess.model.config;

import java.util.List;

public class Config {

    private String name;
    private List<Root> roots;
    private String preconditionExpression;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Root> getRoots() {
        return roots;
    }

    public void setRoots(List<Root> roots) {
        this.roots = roots;
    }

    public String getPreconditionExpression() {
        return preconditionExpression;
    }

    public void setPreconditionExpression(String preconditionExpression) {
        this.preconditionExpression = preconditionExpression;
    }
}
