package de.exxcellent.anothermodule;

import de.exxcellent.java9.jigsaw.spi.BillingService;
import static java.lang.System.out;

public class TestJigsawSPI {

    public static void main(String[] args) {
        BillingService billing = BillingService.getInstance();
        out.println(billing.takeMyMoney());
    }
}