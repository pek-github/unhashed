package org.pek.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.pek.unit.mymap.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    MyMapConstructorTest.class,
    MyMapPutTest.class,
    MyMapContainsKeyTest.class,
    MyMapGetTest.class,
    MyMapClearTest.class
})
public class MyMapSuite {
}
