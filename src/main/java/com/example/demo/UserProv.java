package com.example.demo;

public class UserProv {
    String username, password, repeatPassword;
    Integer age;
    public UserProv(String username,
                    String password,
                    String repeatPassword,
                    Integer age)
    {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.age = age;
    }

    public UserProv(String password, String repeatPassword)
    {
        this.username = "";
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.age = 0;
    }
    public UserProv() {}

    String getUsername()
    {
        return username;
    }
    String getPassword()
    {
        return password;
    }
    String getRepeatPassword()
    {
        return repeatPassword;
    }
    Integer getAge()
    {
        return age;
    }
    void setUsername(String username)
    {
        this.username = username;
    }
    void setPassword(String password)
    {
        this.password = password;
    }
    void setAge(Integer age)
    {
        this.age = age;
    }
    @Override
    public String toString()
    {
        return username + ":" + age;
    }
}
