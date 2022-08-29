package com.sicom.ms.domain.model.dashboardgases.agentlocations;

import lombok.Builder;
import lombok.Value;

import java.sql.Date;

@Value
@Builder(toBuilder = true)
public class AgentLocation {

    int agentId;
    String fuelType;
    Date lastDateRegRate;
    int lastRate;
    int codeSicomAgent;
    String codeSubtypeAgent;
    String conversionType;
    String comercializeProducts;
    String commercialNameAgent;
    String xCoordinate;
    String yCoordinate;
    String department;
    String city;
    String addressType;
    String address;
    String complementaryAddress;
    String phone;



}
