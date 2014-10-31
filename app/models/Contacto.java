package models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Edgar on 28/10/2014.
 */
@Entity
@Table(name = "contacto")
public class Contacto implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario idUsuario;
    @Id
    @ManyToOne
    @JoinColumn(name = "idContacto")
    private Usuario idContacto;

    private String contacto;
    private String telContacto;

    public Contacto(){
        super();
    }

    public Contacto(Usuario idUsuario, Usuario idContacto, String contacto, String telContacto){
        this.setIdUsuario(idUsuario);
        this.setIdContacto(idContacto);
        this.setContacto(contacto);
        this.setTelContacto(telContacto);
    }


    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Usuario idContacto) {
        this.idContacto = idContacto;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelContacto() {
        return telContacto;
    }

    public void setTelContacto(String telContacto) {
        this.telContacto = telContacto;
    }
}
