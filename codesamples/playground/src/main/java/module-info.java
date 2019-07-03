import de.exxcellent.java9.jigsaw.spi.BillingService;

module de.exxcellent.java9 {
    exports de.exxcellent.java9.collections;

    // Depend on an offficial JDK modules
    //    full list see http://cr.openjdk.java.net/~mr/jigsaw/ea/module-summary.html
    requires java.net.http;

    // Service example  (SPI with Jigsaw modules)
    exports de.exxcellent.java9.jigsaw.spi;
    uses BillingService;
}