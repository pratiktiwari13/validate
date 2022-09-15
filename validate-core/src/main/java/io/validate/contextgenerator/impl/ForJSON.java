/* (C)2022 */
package io.validate.contextgenerator.impl;

import io.validate.contextgenerator.ContextGenerator;

public class ForJSON implements ContextGenerator {
    @Override
    public void generate() {
        ForJSON.class.getResourceAsStream("apply.json");
    }
}
