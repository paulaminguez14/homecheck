/*
 * Clase FotoVivienda
 */
package Modelo.Entidades;

import javax.persistence.*;

@Entity
@Table(name = "FOTO_VIVIENDA")
public class FotoVivienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruta;  // Ruta relativa o absoluta de la imagen

    @ManyToOne
    @JoinColumn(name = "vivienda_id")
    private Vivienda vivienda;

    public FotoVivienda() {}

    public FotoVivienda(String ruta, Vivienda vivienda) {
        this.ruta = ruta;
        this.vivienda = vivienda;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }
}

