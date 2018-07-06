package com.association.registration;

public class AssnSubReq {

  @Override
  public String toString() {
    return "AssnSubReq{" +
        "assnname='" + assnname + '\'' +
        ", address1='" + address1 + '\'' +
        ", address2='" + address2 + '\'' +
        ", address3='" + address3 + '\'' +
        ", city='" + city + '\'' +
        ", zipcode='" + zipcode + '\'' +
        ", registrationno='" + registrationno + '\'' +
        ", registrationdate='" + registrationdate + '\'' +
        '}';
  }

  String assnname;
  String address1;
  String address2;

  public String getAssnname() {
    return assnname;
  }

  public void setAssnname(String assnname) {
    this.assnname = assnname;
  }

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getAddress3() {
    return address3;
  }

  public void setAddress3(String address3) {
    this.address3 = address3;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public String getRegistrationno() {
    return registrationno;
  }

  public void setRegistrationno(String registrationno) {
    this.registrationno = registrationno;
  }

  public String getRegistrationdate() {
    return registrationdate;
  }

  public void setRegistrationdate(String registrationdate) {
    this.registrationdate = registrationdate;
  }

  String address3;
  String city;
  String zipcode;
  String registrationno;
  String registrationdate;



}
