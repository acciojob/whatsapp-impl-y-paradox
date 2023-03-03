package com.driver;

public class User {
    private String name;
    private String mobile;


    public User() {

    }

    public User(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /*
    @Override
    public boolean equals(Object that)
    {

        if(this == that)
            return true;


        if(that == null || that.getClass()!= this.getClass())
            return false;


        Group obj = (Group) that;


        return (obj.name.equals(this.name)  && obj.numberOfParticipants == this.numberOfParticipants);
    }

     */
}
