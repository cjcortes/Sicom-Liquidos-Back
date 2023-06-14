package com.sicom.ms.domain.model.dashboardgases.agentlocations;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;

import java.sql.Date;

@Value
@Builder(toBuilder = true)
public class AgentLocation {

    int id;
    int agentId;
    String fuelType;
    String lastDateRegRate;
    String lastRate;
    int codeSicomAgent;
    String codeSubtypeAgent;
    String conversionType;
    String comercializeProducts;
    String commercialNameAgent;
    String coordinateX;
    String coordinateY;
    String department;
    String city;
    String addressType;
    String address;
    String complementaryAddress;
    String phone;

}
