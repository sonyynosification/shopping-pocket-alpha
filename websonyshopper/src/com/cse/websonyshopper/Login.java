package com.cse.websonyshopper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("login")
public class Login {
	// Http Get method
	@GET
	// path: http://localhost/<app-folder-name>/login/dologin?username=abc&password=xyz
	@Produces(MediaType.APPLICATION_JSON)
	// query parameters are parameters: http://localhost/<app-folder-name/login/dologin?username=abc&password=xyz
	public String doLogin(@QueryParam("username") String uname, @QueryParam("password") String pwd){
		String response = "";
		if(checkCredentials(uname,pwd)){
			response = Utility.constructJSON("login", true);
		}
		else{
			response = Utility.constructJSON("login", false, "Incorrect Email or password");
		}
		return response;
	}
	
	/**
     * Method to check whether the entered credential is valid
     * 
     * @param uname
     * @param pwd
     * @return
     */
	private boolean checkCredentials(String uname, String pwd){
		System.out.println("Insite checkCredentials");
		boolean result = false;
		if(Utility.isNotNull(uname) && Utility.isNotNull(pwd)){
			try {
				result = DBConnection.checkLogin(uname, pwd);
			} catch (Exception e) {
				// TODO: handle exception
				result = false;
			}
		}
		return result;
	}
}
