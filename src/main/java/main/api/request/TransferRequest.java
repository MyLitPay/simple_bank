package main.api.request;

public class TransferRequest {
    private String fromAccNumber;
    private String toAccNumber;
    private String amount;

    public TransferRequest() {
    }

    public String getFromAccNumber() {
        return fromAccNumber;
    }

    public void setFromAccNumber(String fromAccNumber) {
        this.fromAccNumber = fromAccNumber;
    }

    public String getToAccNumber() {
        return toAccNumber;
    }

    public void setToAccNumber(String toAccNumber) {
        this.toAccNumber = toAccNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
