package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Edgar on 28/10/2014.
 */
@Entity
@Table(name = "backupdeuda")
public class BackUpDeuda {

    @Id
    private Integer idBackUp;

    private String accion;
    private Integer idDeuda;
    private Integer idUsuario;
    private Boolean estado;
    private String fecha;
    private String descripcion;
    private String contacto;
    private String telContacto;
    private String emailContacto;
    private Integer idUsuarioContacto;
    private String moneda;
    private Double saldo;
    private Boolean relacion;

    public BackUpDeuda(){
        super();
    }

    public BackUpDeuda(Integer idBackUp, String accion, Integer idDeuda,
                       Integer idUsuario, Boolean estado, String fecha,
                       String descripcion, String contacto, String telContacto,
                       String emailContacto, Integer idUsuarioContacto,
                       String moneda, Double saldo, Boolean relacion) {
        this.setIdBackUp(idBackUp);
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


    public Integer getIdBackUp() {
        return idBackUp;
    }

    public void setIdBackUp(Integer idBackUp) {
        this.idBackUp = idBackUp;
    }

    public Integer getIdDeuda() {
        return idDeuda;
    }

    public void setIdDeuda(Integer idDeuda) {
        this.idDeuda = idDeuda;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
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

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public Integer getIdUsuarioContacto() {
        return idUsuarioContacto;
    }

    public void setIdUsuarioContacto(Integer idUsuarioContacto) {
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
