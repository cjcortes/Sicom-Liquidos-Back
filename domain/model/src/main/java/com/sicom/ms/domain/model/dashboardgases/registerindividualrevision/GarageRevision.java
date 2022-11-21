package com.sicom.ms.domain.model.dashboardgases.registerindividualrevision;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class GarageRevision {
    int idTipoRevision;
    String sChip;
    String sPlaca;
    String sNumeroCertificadoSICERCO;
    int idTallerRevision;
    AtestacionPrimeraParte fileAtestacionPrimeraParte;
    CertificadoConformidad fileCertificadoConformidadIns;
    FotoPlacaVehiculo fileFotoPlacaVehiculoRev;
    FotoChip fileFotoChip;
}

@Value
class AtestacionPrimeraParte{
    String fileName;
    String fileData;
}

@Value
class CertificadoConformidad{
    String fileName;
    String fileData;
}

@Value
class FotoPlacaVehiculo{
    String fileName;
    String fileData;
}

@Value
class FotoChip{
    String fileName;
    String fileData;
}