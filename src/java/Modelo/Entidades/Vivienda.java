/*
 * Clase Vivienda.
 */
package Modelo.Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author pauladominguez
 */
@Entity
public class Vivienda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario casero; // Casero que publica la vivienda

    @Column(length = 255, nullable = false)
    private String direccion;

    @Column(nullable = false)
    private double precio;

    @Column(length = 50, nullable = false)
    private String ciudad;

    @Column(length = 50, nullable = false)
    private String provincia;

    @Column(length = 10, nullable = false)
    private String codigoPostal;

    @Column(nullable = false)
    private int habitaciones;

    @Column(nullable = false)
    private int baños;

    @Column(nullable = false)
    private boolean amueblado;

    @Column(nullable = false)
    private boolean activo;
    
    @OneToMany(mappedBy = "vivienda", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Valoracion> valoraciones = new ArrayList<>();

    @OneToMany(mappedBy = "vivienda", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Opinion> comentarios = new ArrayList<>();

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @OneToMany(mappedBy = "vivienda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FotoVivienda> fotos;

    @Column(length = 1000)
    private String descripcion;

    public Vivienda() {
        this.activo = true;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getCasero() {
        return casero;
    }

    public void setCasero(Usuario casero) {
        this.casero = casero;
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

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Opinion> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Opinion> comentarios) {
        this.comentarios = comentarios;
    }

    public List<FotoVivienda> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoVivienda> fotos) {
        this.fotos = fotos;
    }

    @Override
    public String toString() {
        return direccion + " (" + ciudad + ")";
    }
}
