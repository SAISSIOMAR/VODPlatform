package client;

import java.io.Serializable;

public class Client implements Serializable {
    private String mail;
    private String pwd;

    public Client(String mail,String pwd){
        this.mail = mail;
        this.pwd = pwd;
    }

    public String getMail() {
        return mail;
    }


    public String getPwd() {
        return pwd;
    }

}

