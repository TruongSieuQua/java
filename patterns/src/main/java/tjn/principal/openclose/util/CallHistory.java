package tjn.principal.openclose.util;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CallHistory {

    @Getter
    public static class Call {

        /**
         * -- GETTER --
         *
         * @return the begin
         */
        private final LocalDateTime begin;

        /**
         * -- GETTER --
         *
         * @return the duration
         */
        private final long duration;

        /**
         * -- GETTER --
         *
         * @return the subscriber
         */
        private final Long subscriberId;

        public Call(Long subscriberId, LocalDateTime begin, long duration) {
            this.begin = begin;
            this.duration = duration;
            this.subscriberId = subscriberId;
        }

    }

    private static final Map<Long, List<Call>> CALLS = new HashMap<>();

    public synchronized static List<Call> getCurrentCalls(Long subscriberId) {
        if(!CALLS.containsKey(subscriberId)) {
            return Collections.emptyList();
        }
        return CALLS.get(subscriberId);
    }

    public synchronized static void addSession(Long subscriberId, LocalDateTime begin, long duration) {
        List<Call> calls;
        if(!CALLS.containsKey(subscriberId)) {
            calls = new LinkedList<>();
            CALLS.put(subscriberId, calls);
        } else {
            calls = CALLS.get(subscriberId);
        }
        calls.add(new Call(subscriberId, begin, duration));
    }
}
