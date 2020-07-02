package com.sicom.ms.domain.model.error;

import lombok.Value;

import java.io.Serializable;


@Value
public class ApplicationErrorDetail implements Serializable {

    String code;
    String message;

}
