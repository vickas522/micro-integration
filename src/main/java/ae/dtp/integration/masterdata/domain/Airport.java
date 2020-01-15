package ae.dtp.integration.masterdata.domain;

import lombok.Data;

@Data
public class Airport {

    private String iataCode;
    private String icaoCode;
    private String airportName;
    private String airportType;

}
