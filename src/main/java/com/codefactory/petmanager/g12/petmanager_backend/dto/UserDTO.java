package com.codefactory.petmanager.g12.petmanager_backend.dto;

public class UserDTO {
    
    private String idNumber;
    private String idType;
    private String name;
    private int roleId;
    private String phoneNumber;
    private String email;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String idNumber, String idType, String name, int roleId, String phoneNumber, String email, String password) {
        this.idNumber = idNumber;
        this.idType = idType;
        this.name = name;
        this.roleId = roleId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
