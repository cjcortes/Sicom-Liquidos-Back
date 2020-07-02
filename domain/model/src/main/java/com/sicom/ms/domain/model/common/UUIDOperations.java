package com.sicom.ms.domain.model.common;

import java.util.UUID;

public interface UUIDOperations {

    default String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
