/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberinventory.business;


/**
 *
 * @author osheen
 */
public class Supplier {
    private int supID;
    private String supName,supEmail,supAddress,supPhone;
    public Supplier(){
        
    }
    public Supplier(int supId,String supName,String supEmail,String supAddress,String supPhone){
        this.supID=supId;
        this.supEmail=supEmail;
        this.supAddress=supAddress;
        this.supPhone=supPhone;
        this.supName=supName;
    }

    public int getSupID() {
        return supID;
    }

    public void setSupID(int supID) {
        this.supID = supID;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSupEmail() {
        return supEmail;
    }

    public void setSupEmail(String supEmail) {
        this.supEmail = supEmail;
    }

    public String getSupAddress() {
        return supAddress;
    }

    public void setSupAddress(String supAddress) {
        this.supAddress = supAddress;
    }

    public String getSupPhone() {
        return supPhone;
    }

    public void setSupPhone(String supPhone) {
        this.supPhone = supPhone;
    }
}
