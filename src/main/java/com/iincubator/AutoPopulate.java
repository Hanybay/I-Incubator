package com.iincubator;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

/* ___GENERATETESTDATA___ */
@Component // This makes spring consider this class at application startup
public class AutoPopulate {

    @Inject AutoPopulate(AddTestData adder) {
        // NB: to debug this initialization method, sometimes it is better to call it from a controller (so it happen when we do a request, not at init)
        //     ... see the SetupController for that
        adder.generateTestData();
    }
}