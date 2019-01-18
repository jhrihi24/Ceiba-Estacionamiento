package com.ceiba.estacionamiento.trm;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import org.springframework.stereotype.Component;

import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterfaceProxy;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TcrmResponse;

@Component
public class TRMService {
	
	private static final String WEB_SERVICE_URL = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";
	
	public BigDecimal getTrm() throws RemoteException {		
		TCRMServicesInterfaceProxy proxy = new TCRMServicesInterfaceProxy(WEB_SERVICE_URL);
		TcrmResponse tcrmResponse = proxy.queryTCRM(null);
		return BigDecimal.valueOf(tcrmResponse.getValue());		
	}
	
}
