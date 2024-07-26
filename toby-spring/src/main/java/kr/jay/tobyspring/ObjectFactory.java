package kr.jay.tobyspring;

/**
 * ObjectFactory
 *
 * @author jaypark
 * @version 1.0.0
 * @since 7/26/24
 */
public class ObjectFactory {

    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    public ExRateProvider exRateProvider(){
        return new WebApiExRateProvider();
    }
}
