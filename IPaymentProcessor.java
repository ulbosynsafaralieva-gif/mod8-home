package home8;
interface IPaymentProcessor {
    double ProcessPayment(double amount);
}
class PayPalPaymentProcessor implements IPaymentProcessor{
    public double ProcessPayment(double amount) {
        return amount;
    }
}
class StripePaymentService{
    double MakeTransaction(double totalAmount){
        return totalAmount;
    }
}
class StripePaymentAdapter implements IPaymentProcessor{
    StripePaymentService service;

    public StripePaymentAdapter(StripePaymentService service) {
        this.service = service;
    }

    public double ProcessPayment(double amount) {
        return service.MakeTransaction(amount);
    }
}
class Main{
    public static void main(String[] args) {
        IPaymentProcessor processor1 = new PayPalPaymentProcessor();
        IPaymentProcessor processor2 = new StripePaymentAdapter(new StripePaymentService());
        System.out.println("paypal: "+processor1.ProcessPayment(25.3));
        System.out.println("stripepay: "+processor2.ProcessPayment(56.1));
    }
}