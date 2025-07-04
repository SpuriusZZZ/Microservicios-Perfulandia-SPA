package com.usuario.service.usuario_service.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id /*Se establece el atributo id como clave primaria
        o identificador */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   /*
    * @GeneratedValue esta indicando que los valores 
    del id se generarán de forma automatica.
    */
    private int id;
    private String rut;
    private String nombre;
    private String email;
    private String telefono;
    private int rolId;


    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public Usuario() {
        super();
    }
}
