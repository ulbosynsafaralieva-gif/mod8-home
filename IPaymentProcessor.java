package lab8;

public interface IPaymentProcessor
{
    void ProcessPayment(double amount);
    void RefundPayment(double amount);
}
class InternalPaymentProcessor implements IPaymentProcessor{
    public void ProcessPayment(double amount){
        System.out.println("Processing payment of "+amount +" via internal system.");
    }

    public void RefundPayment(double amount){
        System.out.println("Refunding payment of "+amount +" via internal system.");
    }
}

class ExternalPaymentSystemA{
    public void MakePayment(double amount)
    {
        System.out.println("Making payment of "+amount +" via External Payment System A.");
    }

    public void MakeRefund(double amount)
    {
        System.out.println("Making refund of "+amount +" via External Payment System A.");
    }
}



class ExternalPaymentSystemB {
    public void SendPayment(double amount)
    {
        System.out.println("Sending payment of "+amount+ " via External Payment System B.");
    }

    public void ProcessRefund(double amount)
    {
        System.out.println("Processing refund of "+amount+" via External Payment System B.");
    }
}
class PaymentAdapterA implements IPaymentProcessor {
    private ExternalPaymentSystemA _externalSystemA;

    public PaymentAdapterA(ExternalPaymentSystemA externalSystemA) {
        _externalSystemA = externalSystemA;
    }

    public void ProcessPayment(double amount){
        _externalSystemA.MakePayment(amount);
    }

    public void RefundPayment(double amount) {
        _externalSystemA.MakeRefund(amount);
    }
}

class PaymentAdapterB implements IPaymentProcessor {
    private ExternalPaymentSystemB _externalSystemB;

    public PaymentAdapterB(ExternalPaymentSystemB externalSystemB){
        _externalSystemB = externalSystemB;
    }

    public void ProcessPayment(double amount){
        _externalSystemB.SendPayment(amount);
    }

    public void RefundPayment(double amount){
        _externalSystemB.ProcessRefund(amount);
    }
}


class Main2
{
    public static void main(String[] args) {
        IPaymentProcessor internalProcessor = new InternalPaymentProcessor();
        internalProcessor.ProcessPayment(100.0);
        internalProcessor.RefundPayment(50.0);

        ExternalPaymentSystemA externalSystemA = new ExternalPaymentSystemA();
        IPaymentProcessor adapterA = new PaymentAdapterA(externalSystemA);
        adapterA.ProcessPayment(200.0);
        adapterA.RefundPayment(100.0);

        ExternalPaymentSystemB externalSystemB = new ExternalPaymentSystemB();
        IPaymentProcessor adapterB = new PaymentAdapterB(externalSystemB);
        adapterB.ProcessPayment(300.0);
        adapterB.RefundPayment(150.0);
    }
}