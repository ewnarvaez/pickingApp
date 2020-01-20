package com.willer.pickingapp.model;

public class Client {

  private int id;
  private String name;
  private String dni;
  private String city;
  private String mainPhoneNumber;
  private String secondPhoneNumber;
  private String Company;
  private String email;

  public Client() {
  }

  public Client(int id, String name, String dni, String city, String mainPhoneNumber, String secondPhoneNumber, String company, String email) {
    this.id = id;
    this.name = name;
    this.dni = dni;
    this.city = city;
    this.mainPhoneNumber = mainPhoneNumber;
    this.secondPhoneNumber = secondPhoneNumber;
    Company = company;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getMainPhoneNumber() {
    return mainPhoneNumber;
  }

  public void setMainPhoneNumber(String mainPhoneNumber) {
    this.mainPhoneNumber = mainPhoneNumber;
  }

  public String getSecondPhoneNumber() {
    return secondPhoneNumber;
  }

  public void setSecondPhoneNumber(String secondPhoneNumber) {
    this.secondPhoneNumber = secondPhoneNumber;
  }

  public String getCompany() {
    return Company;
  }

  public void setCompany(String company) {
    Company = company;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}