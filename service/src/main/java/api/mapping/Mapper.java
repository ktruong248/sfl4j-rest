package api.mapping;

import api.model.LogLine;

public interface Mapper<T> {
    T mapTo(LogLine logLine);

    LogLine mapFrom(T lineDO);
}
