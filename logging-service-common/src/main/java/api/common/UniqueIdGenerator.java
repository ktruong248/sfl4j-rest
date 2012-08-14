package api.common;

import java.util.UUID;

public class UniqueIdGenerator implements IdGenerator {

    public String randomId() {
        return UUID.randomUUID().toString();
    }
}
