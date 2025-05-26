/*
 * Vivienda Servicio Para API Rest sin dependencias para evitar bucles
 */
package Modelo.Servicio;

import Modelo.Entidades.Vivienda;

/**
 *
 * @author pauladominguez
 */

public class ViviendaDTO {
    private Long id;
    private String direccion;
    private double precio;
    private String ciudad;
    private String provincia;
    private String codigoPostal;
    private int habitaciones;
    private int baños;
    private boolean amueblado;
    private boolean activo;
    private String descripcion;

    public ViviendaDTO(Vivienda vivienda) {
        this.id = vivienda.getId();
        this.direccion = vivienda.getDireccion();
        this.precio = vivienda.getPrecio();
        this.ciudad = vivienda.getCiudad();
        this.provincia = vivienda.getProvincia();
        this.codigoPostal = vivienda.getCodigoPostal();
        this.habitaciones = vivienda.getHabitaciones();
        this.baños = vivienda.getBaños();
        this.amueblado = vivienda.isAmueblado();
        this.activo = vivienda.isActivo();
        this.descripcion = vivienda.getDescripcion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getBaños() {
        return baños;
    }

    public void setBaños(int baños) {
        this.baños = baños;
    }

    public boolean isAmueblado() {
        return amueblado;
    }

    public void setAmueblado(boolean amueblado) {
        this.amueblado = amueblado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
}
