package de.exxcellent.java9.jigsaw.spi;

import java.util.ServiceLoader;

public abstract class BillingService {

    /** Default method to fetch first module-provided implementation. */
    public static BillingService getInstance() {
        return ServiceLoader.load(BillingService.class).findFirst().orElseThrow();
    }

    public abstract String takeMyMoney();
}
