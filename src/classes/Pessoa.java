/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author fabinho
 */
public class Pessoa {
    private String name;
    private String lastName;
    private String CPF;
    private String birthDate;

    public Pessoa() {
    }

    public Pessoa(String name, String lastName, String CPF, String birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.CPF = CPF;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "name=" + name + ", lastName=" + lastName + ", CPF=" + CPF + ", birthDate=" + birthDate + '}';
    }
    
    
    
}
