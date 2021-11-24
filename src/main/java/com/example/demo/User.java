package com.example.demo;

public class User {
    String username, password;
    Integer age;
    public User(String username, String password, Integer age)
    {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public User(User user) {
        this.username = user.username;
        this.password = user.password;
        this.age = user.age;
    }

    String getUsername()
    {
        return username;
    }
    String getPassword()
    {
        return password;
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
        return username + ":" + password + ", " + age;
    }
}
