package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edgar on 07/10/2014.
 */
@Entity
@Table(name="Usuario")
public class Usuario {

    @Id
    @GeneratedValue
    private Integer idUsuario;

    @OneToMany(mappedBy="idUsuario", cascade = CascadeType.MERGE)
    private List<Device> listOfDevices = new ArrayList<Device>();
    @OneToMany(mappedBy="idUsuario", cascade = CascadeType.MERGE)
    private List<Deuda> listOfDeudas = new ArrayList<Deuda>();
    @OneToMany(mappedBy="idUsuarioFrom", cascade = CascadeType.MERGE)
    private List<Notificacion> listOfNotificacion = new ArrayList<Notificacion>();
    @OneToMany(mappedBy="idUsuarioTo", cascade = CascadeType.MERGE)
    private List<Notificacion> listOfNotificacionTo = new ArrayList<Notificacion>();

    private String usuario;
    private String pass;
    private String email;

    public Usuario(){
        this.setIdUsuario(null);
        this.setUsuario(null);
        this.setPass(null);
        this.setEmail(null);
    }

    public Usuario(Integer idUsuario, String usuario, String pass, String email){
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.pass = pass;
        this.email = email;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
