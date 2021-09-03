package com.sicom.ms.domain.model.orders;

        import lombok.Builder;
        import lombok.Value;

@Value
@Builder(toBuilder = true)
public class OPSimpleRequest {

    String domain;
    String userName;
    String process;

}