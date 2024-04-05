package criteria;

import java.time.LocalDateTime;

@FunctionalInterface
public interface TimeIntervalCriteria<T> {

    boolean isWithinInterval(T element, LocalDateTime startTime, LocalDateTime endTime);
}
