package webservice_;
import javax.xml.ws.Endpoint;
import webservice_.hold_impl;

public class hold_serv
{
	public static void main(String[] args)
	{
	   Endpoint.publish("http://138.245.77.4:10080/apex/services/argoservice", new hold_impl());
    }
}

