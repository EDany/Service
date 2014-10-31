package controllers;

import models.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import static play.data.Form.form;

public class Application extends Controller {

    public static Result index(Integer id) {
        /*try{
            EntityConnection.getEntity().getTransaction().begin();
            Usuario user = EntityConnection.getEntity().find(Usuario.class, 1);
            //Query q = EntityConnection.getEntity().createNativeQuery("Select * from device", Device.class);
            //List<Device> d = q.getResultList();
            Query q = EntityConnection.getEntity().createNativeQuery("select * from device where device.telefono = :tel", Device.class);
            q.setParameter("tel", "34545676");
            List<Device> device = q.getResultList();
            if(device != null && device.size()>0) {
                Notificacion n = new Notificacion();
                n.setIdUsuarioFrom(user);
                q = EntityConnection.getEntity().createNativeQuery("select * from usuario where usuario.idUsuario = :id", Usuario.class);
                q.setParameter("id", device.get(0).getIdUsuario().getIdUsuario());
                n.setIdUsuarioTo(EntityConnection.getEntity().find(Usuario.class, device.get(0).getIdUsuario().getIdUsuario()));
                n.setNotificacion(String.valueOf(!true));
                EntityConnection.getEntity().persist(n);
            }
            EntityConnection.getEntity().getTransaction().commit();
            return ok(Json.toJson(q.getResultList()));
        }catch(Exception e){
            return ok(e.toString());
        }*/
        Usuario user = null;
        try {
            EntityConnection.getEntity().getTransaction().begin();
            user = EntityConnection.getEntity().find(Usuario.class, id);
            EntityConnection.getEntity().getTransaction().commit();
        }catch(Exception e){
            return ok(e.toString());
        }
        return ok(Json.toJson(user));
    }

    public static Result signUp() {
        Usuario usuario = new Usuario();
        Device device = new Device();
        Form<Usuario> userFrom = form(Usuario.class);
        Form<Device> deviceFrom = form(Device.class);
        if(!userFrom.hasErrors())
            usuario = userFrom.bindFromRequest().get();
        if(!deviceFrom.hasErrors())
            device = deviceFrom.bindFromRequest().get();
        try {
            EntityConnection.getEntity().getTransaction().begin();
            Query query = EntityConnection.getEntity().createNativeQuery("select * from usuario where usuario.usuario = :user", Usuario.class);
            query.setParameter("user", usuario.getUsuario());
            if(query.getResultList().size()>0) {
                EntityConnection.getEntity().getTransaction().commit();
                return ok("Ya existe este usuario, pruebe con otro.");
            }
            query = EntityConnection.getEntity().createNativeQuery("select * from usuario where usuario.email = :email", Usuario.class);
            query.setParameter("email", usuario.getEmail());
            if(query.getResultList().size()>0) {
                EntityConnection.getEntity().getTransaction().commit();
                return ok("Ya existe una cuenta con este correo.");
            }
            EntityConnection.getEntity().persist(usuario);
            device.setIdUsuario(usuario);
            EntityConnection.getEntity().persist(device);
            EntityConnection.getEntity().getTransaction().commit();
        }catch(Exception e){
            EntityConnection.getEntity().getTransaction().commit();
            return ok(e.toString());
        }
        return ok(Json.toJson(usuario).toString() + Json.toJson(device).toString());
    }

    public static Result signIn(){
        List<Usuario> users = null;
        List<Device> devices = null;
        Form<Usuario> userForm = form(Usuario.class);
        Form<Device> deviceForm = form(Device.class);
        try{
            EntityConnection.getEntity().getTransaction().begin();
            Query query = EntityConnection.getEntity().createNativeQuery("select * from usuario where usuario.usuario = :user and usuario.pass = :pass " +
                                                                         "or usuario.email = :user and usuario.pass = :pass", Usuario.class);
            if(!userForm.hasErrors()) {
                query.setParameter("user", userForm.bindFromRequest().get().getUsuario());
                query.setParameter("pass", userForm.bindFromRequest().get().getPass());
            }
            users = query.getResultList();
            query = EntityConnection.getEntity().createNativeQuery("select * from device where device.idUsuario = :user and device.dispositivo = :dev",
                                                                   Device.class);
            if(!deviceForm.hasErrors() & users.size() > 0){
                query.setParameter("dev", deviceForm.bindFromRequest().get().getDispositivo());
                query.setParameter("user", users.get(0).getIdUsuario());
                devices = query.getResultList();
                if(!(devices.size() > 0)){
                    Device device = deviceForm.bindFromRequest().get();
                    device.setIdUsuario(users.get(0));
                    EntityConnection.getEntity().persist(device);
                }
            }
            EntityConnection.getEntity().getTransaction().commit();
        }catch(Exception e){
            return ok(e.toString());
        }
        if(users.size() > 0)
            return ok(Json.toJson(users.get(0)));
        else
            return ok("Usuario y/o contraseña invalidos.");
    }

    public static Result updateCount(){
        Usuario user = null;
        Form<Usuario> userForm = form(Usuario.class);
        if(!userForm.hasErrors())
            user = userForm.bindFromRequest().get();
        try{
            EntityConnection.getEntity().getTransaction().begin();
            EntityConnection.getEntity().merge(user);
            EntityConnection.getEntity().getTransaction().commit();
        }catch(Exception e){
            return ok(e.toString());
        }
        return ok(Json.toJson(user));
    }

    public static Result sincronizar(Integer id){
        Contacto contacto = null;
        Form<Contacto> contactoForm = form(Contacto.class);
        if(!contactoForm.hasErrors())
            contacto = contactoForm.bindFromRequest().get();
        try{
            EntityConnection.getEntity().getTransaction().begin();
            contacto.setIdUsuario(EntityConnection.getEntity().find(Usuario.class, id));
            if(contacto.getIdUsuario().getSincronizacion()) {
                Query query = EntityConnection.getEntity().createNativeQuery("select * from device where device.telefono = :tel", Device.class);
                query.setParameter("tel", contacto.getTelContacto());
                List<Device> devices = query.getResultList();
                if (devices.size() > 0) {
                    Usuario userContacto = EntityConnection.getEntity().find(Usuario.class, devices.get(0).getIdUsuario().getIdUsuario());
                    query = EntityConnection.getEntity().createNativeQuery("select * from contacto where contacto.idUsuario " +
                            "= :id and contacto.idContacto = :idC", Contacto.class);
                    query.setParameter("id", contacto.getIdUsuario());
                    query.setParameter("idC", userContacto);
                    List<Contacto> contactos = query.getResultList();
                    if (!(contactos.size() > 0)) {
                        contacto.setIdContacto(userContacto);
                        EntityConnection.getEntity().persist(contacto);
                    }
                }
            }
            EntityConnection.getEntity().getTransaction().commit();
        }catch(Exception e){
            return ok(Json.toJson(contacto));
        }
        return ok(Json.toJson(contacto));
    }

    public static Result prueba(Integer id){
        Usuario user = null;
        Form<Json> userForm = form(Json.class);
        try{
            if(!userForm.hasErrors()){
                Json json = userForm.bindFromRequest().get();
                return ok(" "+json);
            }
        }catch(Exception e){
            return ok (e.toString());
        }
        try{
            EntityConnection.getEntity().getTransaction().begin();
            user = EntityConnection.getEntity().find(Usuario.class, id);
            EntityConnection.getEntity().getTransaction().commit();
        }catch(Exception e){
            return ok(e.toString());
        }
        return ok(Json.toJson(user));
    }

    public static Result getDeuda(Integer id){
        Deuda deuda = null;
        try{
            EntityConnection.getEntity().getTransaction().begin();
            deuda = EntityConnection.getEntity().find(Deuda.class, id);
            EntityConnection.getEntity().getTransaction().commit();
        }catch(Exception e){
            return ok(e.toString());
        }
        return ok(Json.toJson(deuda));
    }

    public static Result listDeudas(Integer id){
        List<Deuda> deudas = null;
        try{
            EntityConnection.getEntity().getTransaction().begin();
            Query query = EntityConnection.getEntity().createNativeQuery("select * from deuda where deuda.idUsuario = :id or " +
                                                                         "deuda.idUsuarioContacto = :id", Deuda.class);
            query.setParameter("id", EntityConnection.getEntity().find(Usuario.class, id));
            deudas = query.getResultList();
            EntityConnection.getEntity().getTransaction().commit();
        }catch(Exception e){
            if(EntityConnection.getEntity().isOpen())
                EntityConnection.getEntity().getTransaction().rollback();
            return ok(e.toString());
        }
        return ok(Json.toJson(deudas));
    }

    public static Result createDeuda(Integer id){
        Deuda deuda = null;
        Form<Deuda> deudaForm = form(Deuda.class);
        if (!deudaForm.hasErrors())
            deuda = deudaForm.bindFromRequest().get();
        deuda.setFecha(new SimpleDateFormat("MM-dd-yyyy").format(new GregorianCalendar().getTime()));
        try{
            EntityConnection.getEntity().getTransaction().begin();
            Usuario user = EntityConnection.getEntity().find(Usuario.class, id);
            deuda.setIdUsuario(user);
            if(user.getSincronizacion() == true) {
                Query query = EntityConnection.getEntity().createNativeQuery("select * from contacto where contacto.contacto = :contacto " +
                                                                             "and contacto.telContacto = :tel", Contacto.class);
                query.setParameter("contacto", deuda.getContacto());
                query.setParameter("tel", deuda.getTelContacto());
                List<Contacto> contactos = query.getResultList();
                if (contactos.size() > 0) {
                    deuda.setIdUsuarioContacto(contactos.get(0).getIdContacto());
                    EntityConnection.getEntity().persist(deuda);
                    Notificacion notificacion = new Notificacion();
                    notificacion.setIdUsuarioFrom(deuda.getIdUsuario());
                    notificacion.setIdUsuarioTo(deuda.getIdUsuarioContacto());
                    notificacion.setIdDeuda(deuda);
                    if (deuda.getRelacion())
                        notificacion.setNotificacion("Por favor confirme la siguiente deuda. Fecha: " + deuda.getFecha() + ", pagar a: " +
                                deuda.getIdUsuario().getUsuario() + ", descripcion: " + deuda.getDescripcion() +
                                ", moneda: " + deuda.getMoneda() + ", saldo: " + deuda.getSaldo() + "");
                    else
                        notificacion.setNotificacion("Por favor conirme la siguiente deuda. Fecha: " + deuda.getFecha() + ", deudor: " +
                                deuda.getIdUsuario().getUsuario() + ", descripcion: " + deuda.getDescripcion() +
                                ", moneda: " + deuda.getMoneda() + ", saldo: " + deuda.getSaldo() + "");
                    EntityConnection.getEntity().persist(notificacion);
                }else
                    EntityConnection.getEntity().persist(deuda);
            }else
                EntityConnection.getEntity().persist(deuda);
            EntityConnection.getEntity().getTransaction().commit();
        }catch(Exception e){
            return ok(e.toString());
        }
        return ok(Json.toJson(deuda));
    }

    public static Result updateDeuda(Integer id){
        Deuda deuda = null;
        Form<Deuda> deudaForm = form(Deuda.class);
        if(!deudaForm.hasErrors())
            deuda = deudaForm.bindFromRequest().get();
        try {
            EntityConnection.getEntity().getTransaction().begin();
            EntityConnection.getEntity().merge(deuda);
            if(deuda.getIdUsuarioContacto() != null & deuda.getEstado() == true) {
                Notificacion notificacion = new Notificacion();
                notificacion.setIdDeuda(deuda);
                if (deuda.getIdUsuario().getIdUsuario() == id) {
                    notificacion.setIdUsuarioFrom(deuda.getIdUsuario());
                    notificacion.setIdUsuarioTo(deuda.getIdUsuarioContacto());
                    notificacion.setNotificacion(deuda.getIdUsuario().getUsuario()+" ha realizado los siguientes cambios.");
                } else {
                    notificacion.setIdUsuarioFrom(deuda.getIdUsuarioContacto());
                    notificacion.setIdUsuarioTo(deuda.getIdUsuario());
                    notificacion.setNotificacion(deuda.getIdUsuarioContacto().getUsuario()+" ha realizado los siguientes cambios.");
                }
                EntityConnection.getEntity().persist(notificacion);
            }
            EntityConnection.getEntity().getTransaction().commit();
        }catch(Exception e){
            if(EntityConnection.getEntity().isOpen())
                EntityConnection.getEntity().getTransaction().rollback();
            return ok(e.toString());
        }
        return ok("Cambios realizados exitosamente !" + Json.toJson(deuda));
    }

    public static Result deleteDeuda(Integer id){
        Deuda deuda = null;
        Form<Deuda> deudaForm = form(Deuda.class);
        if(!deudaForm.hasErrors())
            deuda = deudaForm.bindFromRequest().get();
        try{
            EntityConnection.getEntity().getTransaction().begin();
            if(deuda.getIdUsuarioContacto() != null){
                Notificacion notificacion = new Notificacion();
                if(deuda.getIdUsuario().getIdUsuario() == id) {
                    notificacion.setIdUsuarioFrom(deuda.getIdUsuario());
                    notificacion.setIdUsuarioTo(deuda.getIdUsuarioContacto());
                    notificacion.setNotificacion(deuda.getIdUsuario()+" ha eliminado la sigiuente deuda.");
                }else{
                    notificacion.setIdUsuarioFrom(deuda.getIdUsuarioContacto());
                    notificacion.setIdUsuarioTo(deuda.getIdUsuario());
                    notificacion.setNotificacion(deuda.getIdUsuarioContacto()+" ha eliminado la siguiente deuda");
                }
                EntityConnection.getEntity().persist(notificacion);
            }
            EntityConnection.getEntity().remove(deuda);
            EntityConnection.getEntity().getTransaction().commit();
        }catch(Exception e){
            if(EntityConnection.getEntity().isOpen())
                EntityConnection.getEntity().getTransaction().commit();
            return ok(e.toString());
        }
        return ok();
    }

    public static Result confirmarDeuda(Integer idU){
        Deuda deuda = null;
        Form<Deuda> deudaForm = form(Deuda.class);
        if(!deudaForm.hasErrors())
            deuda = deudaForm.bindFromRequest().get();
        try{
            EntityConnection.getEntity().getTransaction().begin();
            deuda.setIdUsuarioContacto(EntityConnection.getEntity().find(Usuario.class, idU));
            deuda.setEstado(true);
            EntityConnection.getEntity().merge(deuda);
            EntityConnection.getEntity().getTransaction().commit();
        }catch(Exception e){
            return ok(e.toString());
        }
        return ok();
    }

    public static Result listNotificaciones(Integer id){
        List<Notificacion> notificaciones = null;
        try{
            EntityConnection.getEntity().getTransaction().begin();
            Query query = EntityConnection.getEntity().createNativeQuery("select * from notificacion where notificacion.idUsuarioTo = :id",
                    Notificacion.class);
            query.setParameter("id", EntityConnection.getEntity().find(Usuario.class, id));
            notificaciones = query.getResultList();
            EntityConnection.getEntity().getTransaction().commit();
        }catch(Exception e){
            if(EntityConnection.getEntity().isOpen())
                EntityConnection.getEntity().getTransaction().rollback();
            return ok(e.toString());
        }
        return ok(Json.toJson(notificaciones));
    }
}
