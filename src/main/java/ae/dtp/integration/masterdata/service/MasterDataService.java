package ae.dtp.integration.masterdata.service;
import ae.dtp.integration.masterdata.domain.Airport;
import ae.dtp.integration.masterdata.schema.MSG;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@Component
public class MasterDataService {

    public String generateAirportStrMsg (Airport airport)
    {
        JAXBContext jaxbContext;
        String aptxmlresponse = " " ;
        StringWriter sw = new StringWriter();
        MSG airportMsg = generateAirportMsg(airport);
        try
        {
            jaxbContext = JAXBContext.newInstance(MSG.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(airportMsg,sw);
            aptxmlresponse = sw.toString() ;
        } catch (JAXBException e)
        {
            e.printStackTrace();
        }
        System.out.println("response XML :: "+aptxmlresponse);
        return aptxmlresponse ;
    }


    public MSG generateAirportMsg(Airport airport)
    {

        System.out.println("recieved onto service layer" );
        MSG airportMsg = new MSG();
        MSG.MSGSTREAMOUT msgstreamout = new MSG.MSGSTREAMOUT();
        MSG.MSGSTREAMOUT.INFOBJGENERIC infobjgen = new MSG.MSGSTREAMOUT.INFOBJGENERIC();
        MSG.MSGSTREAMOUT.MASTER masterdata = new MSG.MSGSTREAMOUT.MASTER();
        MSG.MSGSTREAMOUT.MASTER.STATICAIRPORT staticairport = new  MSG.MSGSTREAMOUT.MASTER.STATICAIRPORT();
        MSG.MSGSTREAMOUT.MASTER.STATICAIRPORT.AIRPORT airportdetails = new MSG.MSGSTREAMOUT.MASTER.STATICAIRPORT.AIRPORT();
        infobjgen.setMESSAGETYPE("AOSAPT");
        infobjgen.setMESSAGEORIGIN("DAAOS");
        infobjgen.setTIMEID("UTC");
        infobjgen.setACTIONTYPE("I");
        airportdetails.setAPC3(airport.getIataCode());
        airportdetails.setAPC4(airport.getIcaoCode());
        airportdetails.setAPFN(airport.getAirportName());
        airportdetails.setAPTT(airport.getAirportType());
        System.out.println("APFN ::" + airport.getAirportName() );
        staticairport.setAIRPORT(airportdetails);
        masterdata.setSTATICAIRPORT(staticairport);
        msgstreamout.setMASTER(masterdata);
        msgstreamout.setINFOBJGENERIC(infobjgen);
        airportMsg.setMSGSTREAMOUT(msgstreamout);
        System.out.println("airportmsg obj ::" + airportMsg );
        return airportMsg ;

    }
}
