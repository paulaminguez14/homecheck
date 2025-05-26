/*
 * Clase Provincias
 */
package Modelo.Entidades;

/**
 *
 * @author pauladominguez
 */

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "provincias")
public class Provincias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ciudades> ciudades;

    public Provincias() {}

    public Provincias(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ciudades> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudades> ciudades) {
        this.ciudades = ciudades;
    }
}