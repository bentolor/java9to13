package de.exxcellent.anothermodule.spi;

import de.exxcellent.java9.jigsaw.spi.BillingService;

public class MastercardBillingService extends BillingService {

    @Override
    public String takeMyMoney() {
        return "Mastercard billed the money!";
    }
}