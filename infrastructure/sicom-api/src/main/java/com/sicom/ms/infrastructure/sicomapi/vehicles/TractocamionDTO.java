package com.sicom.ms.infrastructure.sicomapi.vehicles;

public class TractocamionDTO {

    public String idM_Asociacion;
    public String estadoVehiculo;
    public String tipoPropiedad;
    public String identificador;
    public String empresaTransportadora;
    public String placaTanque;
    public String nombreConductor;
    public String numeroDocumentoConductor;
    public RemolqueTractoCamion remolqueTractoCamion;

}

class RemolqueTractoCamion {
    public String capacidad;
    public int numeroCompartimientos;
}