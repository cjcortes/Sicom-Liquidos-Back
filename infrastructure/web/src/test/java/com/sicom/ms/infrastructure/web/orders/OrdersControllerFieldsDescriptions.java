package com.sicom.ms.infrastructure.web.orders;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class OrdersControllerFieldsDescriptions {

    private static final String AUTH_CODE_DESCRIPTION = "Código de autorización";
    private static final String CLIENT_CODE_DESCRIPTION = "Código SICOM cliente";
    private static final String PROVIDER_SICOM_PLANT_DESCRIPTION = "Código SICOM de la planta proveedor";
    private static final String ORDER_TYPE_DESCRIPTION = "Tipo de orden";
    private static final String ORDER_STATE_DESCRIPTION = "Estado de la orden";
    private static final String SUGGESTED_DELIVERY_START_DATE_DESCRIPTION = "Fecha de inicio de entrega sugerida";
    private static final String SUGGESTED_DELIVERY_END_DATE_DESCRIPTION = "Fecha de finalización de entrega sugerida";

    protected static final ParameterDescriptor[] ORDER_FILTERS_DESCRIPTOR = new ParameterDescriptor[]{
            parameterWithName("authCode")
                    .description(AUTH_CODE_DESCRIPTION),
            parameterWithName("clientCode")
                    .description(CLIENT_CODE_DESCRIPTION),
            parameterWithName("providerPlantCode")
                    .description(PROVIDER_SICOM_PLANT_DESCRIPTION),
            parameterWithName("orderType")
                    .description(ORDER_TYPE_DESCRIPTION),
            parameterWithName("orderState")
                    .description(ORDER_STATE_DESCRIPTION),
            parameterWithName("suggestedDeliveryStartDate")
                    .description(SUGGESTED_DELIVERY_START_DATE_DESCRIPTION),
            parameterWithName("suggestedDeliveryEndDate")
                    .description(SUGGESTED_DELIVERY_END_DATE_DESCRIPTION)
    };

    private static final String AUTHORIZATION_CODE_DESCRIPTION = "Código de autorización";
    private static final String HEAD_PLATE_DESCRIPTION = "Placa del cabezote";
    private static final String TRAILER_DESCRIPTION = "Remolque";
    private static final String TRANSPORT_TYPE_DESCRIPTION = "Tipo de transporte";
    private static final String DRIVER_DESCRIPTION = "Nombre del conductor";
    private static final String DRIVER_IDENTIFICATION_DESCRIPTION = "Cédula del conductor";
    private static final String TRANSPORT_CODE_DESCRIPTION = "Código del transporte";
    private static final String APPLICATION_DATE_DESCRIPTION = "Fecha de la solicitud en Millis";
    private static final String DISPATCH_DATE_DESCRIPTION = "Fecha de despacho en Millis";

    protected static final FieldDescriptor[] ORDER_DESCRIPTOR = new FieldDescriptor[]{
            fieldWithPath("[].authorizationCode")
                    .type(JsonFieldType.STRING)
                    .description(AUTHORIZATION_CODE_DESCRIPTION),
            fieldWithPath("[].headPlate")
                    .type(JsonFieldType.STRING)
                    .description(HEAD_PLATE_DESCRIPTION),
            fieldWithPath("[].trailer")
                    .type(JsonFieldType.STRING)
                    .description(TRAILER_DESCRIPTION),
            fieldWithPath("[].transportType")
                    .type(JsonFieldType.STRING)
                    .description(TRANSPORT_TYPE_DESCRIPTION),
            fieldWithPath("[].driver")
                    .type(JsonFieldType.STRING)
                    .description(DRIVER_DESCRIPTION),
            fieldWithPath("[].driverIdentification")
                    .type(JsonFieldType.STRING)
                    .description(DRIVER_IDENTIFICATION_DESCRIPTION),
            fieldWithPath("[].transportCode")
                    .type(JsonFieldType.NUMBER)
                    .description(TRANSPORT_CODE_DESCRIPTION),
            fieldWithPath("[].applicationDate")
                    .type(JsonFieldType.STRING)
                    .description(APPLICATION_DATE_DESCRIPTION),
            fieldWithPath("[].dispatchDate")
                    .type(JsonFieldType.STRING)
                    .description(DISPATCH_DATE_DESCRIPTION),
            fieldWithPath("[].orderType")
                    .type(JsonFieldType.STRING)
                    .description(ORDER_TYPE_DESCRIPTION)
    };

    private static final String ORDER_DATA_DESCRIPTION = "Datos de la orden";
    private static final String ORDER_CODE_DESCRIPTION = "Código de la orden";
    private static final String STATE_DESCRIPTION = "Estado de la orden";
    private static final String OBSERVATION_DESCRIPTION = "Observaciones de la orden";
    private static final String SUGGESTED_DATE_DESCRIPTION = "Fecha sugerida";
    private static final String BILL_NUMBER_DESCRIPTION = "Número de la factura";
    private static final String TRANSPORT_GUIDE_DESCRIPTION = "Guía del transporte";
    private static final String PLANT_GUIDE_DESCRIPTION = "Guía de la planta";
    private static final String GUIDE_VALIDITY_DESCRIPTION = "Validez de la guía";
    private static final String BORDER_QUOTA_DESCRIPTION = "Cupo frontera";
    private static final String NATIONAL_QUOTA_DESCRIPTION = "Cupo nacional";
    private static final String ADDITIONAL_OBSERVATIONS_DESCRIPTION = "Observaciones adicionales";

    private static final String PROVIDER_CUSTOMER_DESCRIPTION = "Datos del cliente y el proveedor";
    private static final String CUSTOMER_TYPE_DESCRIPTION = "Tipo de cliente";
    private static final String CUSTOMER_SUBTYPE_DESCRIPTION = "Subtipo de cliente";
    private static final String CUSTOMER_NAME_DESCRIPTION = "Nombre del cliente";
    private static final String CUSTOMER_SICOM_DESCRIPTION = "Código SICOM del cliente";
    private static final String CUSTOMER_NIT_DESCRIPTION = "NIT del cliente";
    private static final String CUSTOMER_DEPARTMENT_DESCRIPTION = "Departamento del cliente";
    private static final String CUSTOMER_MUNICIPALITY_DESCRIPTION = "Municipio del cliente";
    private static final String CUSTOMER_ADDRESS_DESCRIPTION = "Direccción del cliente";
    private static final String PROVIDER_SICOM_DESCRIPTION = "Código SICOM del proveedor";
    private static final String PROVIDER_NAME_DESCRIPTION = "Nombre del proveedor";
    private static final String PROVIDER_TYPE_DESCRIPTION = "Tipo de proveedor";
    private static final String PROVIDER_SUBTYPE_DESCRIPTION = "Subtipo de proveedor";
    private static final String PROVIDER_NAME_PLANT_DESCRIPTION = "Nombre de la planta proveedor";

    private static final String VEHICLE_DESCRIPTION = "Datos del vehículo";
    private static final String CAPACITY_DESCRIPTION = "Capacidad del vehículo";
    private static final String COMPARTMENT_DESCRIPTION = "Compartimiento del vehículo";


    protected static final FieldDescriptor[] ORDER_DETAIL_DESCRIPTOR = new FieldDescriptor[]{
            // Order data
            fieldWithPath("orderInfo")
                    .type(JsonFieldType.OBJECT)
                    .description(ORDER_DATA_DESCRIPTION),
            fieldWithPath("orderInfo.orderCode")
                    .type(JsonFieldType.NUMBER)
                    .description(ORDER_CODE_DESCRIPTION),
            fieldWithPath("orderInfo.orderType")
                    .type(JsonFieldType.STRING)
                    .description(ORDER_TYPE_DESCRIPTION),
            fieldWithPath("orderInfo.authorizationCode")
                    .type(JsonFieldType.STRING)
                    .description(AUTH_CODE_DESCRIPTION),
            fieldWithPath("orderInfo.state")
                    .type(JsonFieldType.STRING)
                    .description(STATE_DESCRIPTION),
            fieldWithPath("orderInfo.observation")
                    .type(JsonFieldType.STRING)
                    .description(OBSERVATION_DESCRIPTION),
            fieldWithPath("orderInfo.suggestedDate")
                    .type(JsonFieldType.STRING)
                    .description(SUGGESTED_DATE_DESCRIPTION),
            fieldWithPath("orderInfo.billNumber")
                    .type(JsonFieldType.STRING)
                    .description(BILL_NUMBER_DESCRIPTION),
            fieldWithPath("orderInfo.transportGuide")
                    .type(JsonFieldType.STRING)
                    .description(TRANSPORT_GUIDE_DESCRIPTION),
            fieldWithPath("orderInfo.plantGuide")
                    .type(JsonFieldType.STRING)
                    .description(PLANT_GUIDE_DESCRIPTION),
            fieldWithPath("orderInfo.guideValidity")
                    .type(JsonFieldType.STRING)
                    .description(GUIDE_VALIDITY_DESCRIPTION),
            fieldWithPath("orderInfo.transportType")
                    .type(JsonFieldType.STRING)
                    .description(TRANSPORT_TYPE_DESCRIPTION),
            fieldWithPath("orderInfo.borderQuota")
                    .type(JsonFieldType.STRING)
                    .description(BORDER_QUOTA_DESCRIPTION),
            fieldWithPath("orderInfo.nationalQuota")
                    .type(JsonFieldType.STRING)
                    .description(NATIONAL_QUOTA_DESCRIPTION),
            fieldWithPath("orderInfo.additionalObservation")
                    .type(JsonFieldType.STRING)
                    .description(ADDITIONAL_OBSERVATIONS_DESCRIPTION),
            // Provider and customer data
            fieldWithPath("providerCustomer")
                    .type(JsonFieldType.OBJECT)
                    .description(PROVIDER_CUSTOMER_DESCRIPTION),
            fieldWithPath("providerCustomer.orderCode")
                    .type(JsonFieldType.NUMBER)
                    .description(ORDER_CODE_DESCRIPTION),
            fieldWithPath("providerCustomer.customerType")
                    .type(JsonFieldType.STRING)
                    .description(CUSTOMER_TYPE_DESCRIPTION),
            fieldWithPath("providerCustomer.customerSubtype")
                    .type(JsonFieldType.STRING)
                    .description(CUSTOMER_SUBTYPE_DESCRIPTION),
            fieldWithPath("providerCustomer.customerName")
                    .type(JsonFieldType.STRING)
                    .description(CUSTOMER_NAME_DESCRIPTION),
            fieldWithPath("providerCustomer.customerSicom")
                    .type(JsonFieldType.STRING)
                    .description(CUSTOMER_SICOM_DESCRIPTION),
            fieldWithPath("providerCustomer.customerNit")
                    .type(JsonFieldType.STRING)
                    .description(CUSTOMER_NIT_DESCRIPTION),
            fieldWithPath("providerCustomer.customerDepartment")
                    .type(JsonFieldType.STRING)
                    .description(CUSTOMER_DEPARTMENT_DESCRIPTION),
            fieldWithPath("providerCustomer.customerMunicipality")
                    .type(JsonFieldType.STRING)
                    .description(CUSTOMER_MUNICIPALITY_DESCRIPTION),
            fieldWithPath("providerCustomer.customerAddress")
                    .type(JsonFieldType.STRING)
                    .description(CUSTOMER_ADDRESS_DESCRIPTION),
            fieldWithPath("providerCustomer.providerSicom")
                    .type(JsonFieldType.STRING)
                    .description(PROVIDER_SICOM_DESCRIPTION),
            fieldWithPath("providerCustomer.providerName")
                    .type(JsonFieldType.STRING)
                    .description(PROVIDER_NAME_DESCRIPTION),
            fieldWithPath("providerCustomer.providerType")
                    .type(JsonFieldType.STRING)
                    .description(PROVIDER_TYPE_DESCRIPTION),
            fieldWithPath("providerCustomer.providerSubtype")
                    .type(JsonFieldType.STRING)
                    .description(PROVIDER_SUBTYPE_DESCRIPTION),
            fieldWithPath("providerCustomer.providerSicomPlant")
                    .type(JsonFieldType.STRING)
                    .description(PROVIDER_SICOM_PLANT_DESCRIPTION),
            fieldWithPath("providerCustomer.providerNamePlant")
                    .type(JsonFieldType.STRING)
                    .description(PROVIDER_NAME_PLANT_DESCRIPTION),
            // Vehicle data
            fieldWithPath("vehicle")
                    .type(JsonFieldType.OBJECT)
                    .description(VEHICLE_DESCRIPTION),
            fieldWithPath("vehicle.transportType")
                    .type(JsonFieldType.STRING)
                    .description(TRANSPORT_TYPE_DESCRIPTION),
            fieldWithPath("vehicle.headPlate")
                    .type(JsonFieldType.STRING)
                    .description(HEAD_PLATE_DESCRIPTION),
            fieldWithPath("vehicle.trailer")
                    .type(JsonFieldType.STRING)
                    .description(TRAILER_DESCRIPTION),
            fieldWithPath("vehicle.capacity")
                    .type(JsonFieldType.NUMBER)
                    .description(CAPACITY_DESCRIPTION),
            fieldWithPath("vehicle.driver")
                    .type(JsonFieldType.STRING)
                    .description(DRIVER_DESCRIPTION),
            fieldWithPath("vehicle.driverIdentification")
                    .type(JsonFieldType.STRING)
                    .description(DRIVER_IDENTIFICATION_DESCRIPTION),
            fieldWithPath("vehicle.compartment")
                    .type(JsonFieldType.NUMBER)
                    .description(COMPARTMENT_DESCRIPTION),
    };

    private static final String PRODUCT_NAME_DESCRIPTION = "Nombre del producto";
    private static final String PRODUCT_STATE_DESCRIPTION = "Estado del producto";
    private static final String REQUESTED_AMOUNT_DESCRIPTION = "Volumen solicitado";
    private static final String ACCEPTED_AMOUNT_DESCRIPTION = "Volumen aceptado";
    private static final String DISPATCHED_AMOUNT_DESCRIPTION = "Volumen despachado";

    protected static final FieldDescriptor[] PRODUCT_DESCRIPTOR = new FieldDescriptor[]{
            fieldWithPath("[].name")
                    .type(JsonFieldType.STRING)
                    .description(PRODUCT_NAME_DESCRIPTION),
            fieldWithPath("[].state")
                    .type(JsonFieldType.STRING)
                    .description(PRODUCT_STATE_DESCRIPTION),
            fieldWithPath("[].requestedAmount")
                    .type(JsonFieldType.NUMBER)
                    .description(REQUESTED_AMOUNT_DESCRIPTION),
            fieldWithPath("[].acceptedAmount")
                    .type(JsonFieldType.NUMBER)
                    .description(ACCEPTED_AMOUNT_DESCRIPTION),
            fieldWithPath("[].dispatchedAmount")
                    .type(JsonFieldType.NUMBER)
                    .description(DISPATCHED_AMOUNT_DESCRIPTION),
    };
}
