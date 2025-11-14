package org.epam.swaglabs;

public class User {
    private String username;
    private String password;
    public static final String USERNAME_PROPERTY = "user.name";
    private static final String USER_PASSWORD = "secret_sauce";
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public static User createUserFromProperties(){
        return new User(DataReader.getData(USERNAME_PROPERTY), USER_PASSWORD);
    }

}
