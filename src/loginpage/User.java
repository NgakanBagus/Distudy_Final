/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginpage;

/**
 *
 * @author Basudewa
 */
public class User {
    private String username;
    private String email;
    private String role;
    private int id;

    public User(String username, String role, int id, String email) {
        this.username = username;
        this.role = role;
        this.id = id;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
    
    public int getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
}
