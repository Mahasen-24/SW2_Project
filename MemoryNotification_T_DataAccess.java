package infrastructure;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.notification;
import model.response;


@Path("/notification")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class MemoryNotification_T_DataAccess implements INotification_T_DataAccess{
 
	private static Map<Integer,notification> templetes=new HashMap<Integer,notification>();

	
	@Override
	@GET
	@Path("/{id}/read")
	public notification ReadNotification(@PathParam("id") int id) {
		
		return templetes.get(id);
	}

	@Override
	@POST
	@Path("/create")
	public response CreateNotification(notification n) {
		response r=new response();
		if(templetes.get(n.getId())!=null)
		{
			r.setStatus(false);
			return r;
		}
		else
		{
			templetes.put(n.getId(),n);
			r.setStatus(true);
			return r;
		}
	}

	@Override
	@POST
	@Path("/{id}/update")
	public response UpdateNotification(@PathParam("id")int id,notification n) {
		response r=new response();
		if(templetes.get(id)==null)
		{
			r.setStatus(false);
			return r;
		}
		else
		{
			templetes.put(id,n);
			r.setStatus(true);
			return r;
		}
	}

	@Override
	@GET
	@Path("/{id}/delete")
	public response deleteNotification(@PathParam("id") int id) {
		response r=new response();
		if(templetes.get(id)==null)
		{
			r.setStatus(false);
			return r;
		}
		else
		{
			templetes.remove(id);
			r.setStatus(true);
			return r;
		}
	}
	

}
