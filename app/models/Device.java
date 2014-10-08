package models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Edgar on 07/10/2014.
 */
@Entity
@Table(name="device")
public class Device implements Serializable{

    @Id
    private String dispositivo;
    @Id
    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario idUsuario;

    private String telefono;

    public Device(){
        this.setDispositivo(null);
        this.setIdUsuario(null);
        this.setTelefono(null);
    }

    public Device(String dispositivo, Usuario idUsuario, String telefono){
        this.setDispositivo(dispositivo);
        this.setIdUsuario(idUsuario);
        this.setTelefono(telefono);
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
