
package com.setico.builditbigger;

import junit.framework.TestSuite;
import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;

public class FullTestSuite extends TestSuite {
    public static Test suite() {
        return new TestSuiteBuilder(FullTestSuite.class)
                .includeAllPackagesUnderHere().build();
    }

    public FullTestSuite() {
        super();
    }
}
