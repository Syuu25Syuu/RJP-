package test1.been;

import java.io.Serializable;

public class Login_Been implements Serializable{
	private String name;
    private String pass;
    private String sessionToken;

    public Login_Been() {}


    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPass(){
        return pass;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    public void setSessionToken(String sessionToken) {
    	this.sessionToken = sessionToken;
    }
    public String getSessionToken() {
    	//System.out.println("Bean内のTokenは"+sessionToken+"です");
    	return sessionToken;
    }
}
