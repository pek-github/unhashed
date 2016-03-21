package org.pek.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.pek.unit.myset.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    MySetBasicTest.class,
    MySetConstructorsTest.class,
    MySetAddTest.class,
    MySetClearTest.class,
    MySetContainsTest.class,
    MySetRemoveTest.class,
    MySetRetainTest.class,
    MySetToArrayTest.class,
    MySetIteratorTest.class
})
public class MySetSuite {
}
