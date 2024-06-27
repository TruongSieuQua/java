package tjn.principal.openclose;

import lombok.Getter;
import lombok.Setter;

//base class - closed for modification
@Setter
@Getter
public abstract class Subscriber {

    protected Long subscriberId;

    protected String address;

    protected Long phoneNumber;

    protected int baseRate;

    public abstract double calculateBill(); //extension

}
