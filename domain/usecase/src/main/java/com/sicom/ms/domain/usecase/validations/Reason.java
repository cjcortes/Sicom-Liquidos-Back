package com.sicom.ms.domain.usecase.validations;

import lombok.Value;

@Value(staticConstructor = "of")
public class Reason {

    String code;
    String message;

}
