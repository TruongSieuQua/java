package tjn.principal.openclose;

import tjn.principal.openclose.util.CallHistory;

import java.util.List;

public class PhoneSubscriber extends Subscriber {

    //only for demonstration - open for extension
    @Override
    public double calculateBill() {
        List<CallHistory.Call> sessions = CallHistory.getCurrentCalls(subscriberId);
        long totalDuration = sessions.stream().mapToLong(CallHistory.Call::getDuration).sum();
        return (double) (totalDuration * baseRate) /100;
    }

}
