package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static Result index() {
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
        return ok(views.html.index.render("jajajajaja"));
    }

}
