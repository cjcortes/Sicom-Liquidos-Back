package com.sicom.ms.domain.model.orderinfo;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class OrderInfo {

    int orderCode;
    String orderType;
    String authorizationCode;
    String state;
    String observation;
    String suggestedDate;
    String billNumber;
    String transportGuide;
    String plantGuide;
    String guideValidity;
    String transportType;
    String borderQuota;
    String nationalQuota;
    String additionalObservation;
    String receiptPlantId;
    String supplyPlantId;
    String providesTransport;
    String radNumber;
    String vehicleAgent;
    String vehicleId;

}
