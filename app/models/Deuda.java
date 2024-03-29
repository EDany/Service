package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Edgar on 07/10/2014.
 */
@Entity
@Table(name="deuda")
public class Deuda implements Serializable{

    @Id
    @GeneratedValue
    private Integer idDeuda;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "idUsuarioContacto")
    private Usuario idUsuarioContacto;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDeuda")
    private List<Notificacion> listOfNotifications = new ArrayList<Notificacion>();

    private Boolean estado;

    private String fecha;
    private String descripcion;
    private String contacto;
    private String telContacto;
    private String emailContacto;

    private String moneda;
    private Double saldo;
    private Boolean relacion;

    public Deuda() {
        super();
    }

    public Deuda(Integer idDeuda, Usuario idUsuario, Boolean estado, String fecha, String descripcion,
                 String contacto, String telContacto, String emailContacto, Usuario idUsuarioContacto,
                 String moneda, Double saldo, Boolean relacion){
        this.setIdDeuda(idDeuda);
        this.setIdUsuario(idUsuario);
        this.setEstado(estado);
        this.setFecha(fecha);
        this.setDescripcion(descripcion);
        this.setContacto(contacto);
        this.setTelContacto(telContacto);
        this.setEmailContacto(emailContacto);
        this.setIdUsuarioContacto(idUsuarioContacto);
        this.setMoneda(moneda);
        this.setSaldo(saldo);
        this.setRelacion(relacion);
    }

    public Integer getIdDeuda() {
        return idDeuda;
    }

    public void setIdDeuda(Integer idDeuda) {
        this.idDeuda = idDeuda;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getEmailContacto(){
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto){
        this.emailContacto = emailContacto;
    }

    public Usuario getIdUsuarioContacto(){
        return this.idUsuarioContacto;
    }

    public void setIdUsuarioContacto(Usuario idUsuarioContacto){
        this.idUsuarioContacto = idUsuarioContacto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean getRelacion() {
        return relacion;
    }

    public void setRelacion(Boolean relacion) {
        this.relacion = relacion;
    }
}
