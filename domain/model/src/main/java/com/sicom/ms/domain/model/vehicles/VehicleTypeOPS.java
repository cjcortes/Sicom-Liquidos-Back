package com.sicom.ms.domain.model.vehicles;

public enum VehicleTypeOPS
{
    SHIP("Buque"),
    BARGE("Barcaza"),
    TANK_CAR("Carro Tanque"),
    TRACTOR_TRUCK("Tractocamión");

    private String value;

    VehicleTypeOPS(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}