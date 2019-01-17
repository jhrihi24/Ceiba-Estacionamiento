package com.ceiba.estacionamiento.trm;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterfaceProxy;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TcrmResponse;

@Component
public class TRMWebService {
		
	static Logger perfLog = LoggerFactory.getLogger(TRMWebService.class);
	private static final String WEB_SERVICE_URL = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";
	
	public BigDecimal getTrmActual() {
		BigDecimal trm= new BigDecimal(0);
		try{
			TCRMServicesInterfaceProxy proxy = new TCRMServicesInterfaceProxy(WEB_SERVICE_URL);
			TcrmResponse tcrmResponse = proxy.queryTCRM(null);
			trm= BigDecimal.valueOf(tcrmResponse.getValue());
		}catch (RemoteException e) {
			perfLog.warn(e.getMessage());
		}
		
		return trm;
	}
	
}
