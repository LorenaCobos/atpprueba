package webservice_;

import javax.jws.WebService;

@WebService(endpointInterface = "webservice_.hold_")
public class hold_impl
{
	public String getAsString(String name)
	{
		return "-------- " + name;
	}
}
