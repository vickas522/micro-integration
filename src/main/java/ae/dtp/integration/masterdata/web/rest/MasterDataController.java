package ae.dtp.integration.masterdata.web.rest;

import ae.dtp.integration.masterdata.domain.Airport;
import ae.dtp.integration.masterdata.service.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/masters/transformer")
public class MasterDataController {

    @Autowired
    private MasterDataService masterdataservice ;


    @RequestMapping(value = "/airports" , method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.TEXT_PLAIN_VALUE)
    public String generateResponse(@RequestBody final Airport airport )
    {

        System.out.println("recieved control with json req" +airport );
        return  masterdataservice.generateAirportStrMsg(airport);

    }

}
