package Web;



import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import infrastructure.INotification_T_DataAccess;
import infrastructure.MemoryNotification_T_DataAccess;
import model.notification;
import model.response;

@Path("/notification")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class NotificationCRUDController {

private INotification_T_DataAccess DataAccess=new MemoryNotification_T_DataAccess();
	
	@GET
	@Path("/{id}/Get")
	public notification Get(@PathParam("id") int id)
	{
		return DataAccess.ReadNotification(id);
	}
	@POST
	@Path("/Add")
	public response Add(notification n)
	{
		return DataAccess.CreateNotification(n);
	}
	
	@POST
	@Path("/{id}/Update")
	public response Update(@PathParam("id")int id,notification n)
	{
		 return DataAccess.UpdateNotification(id, n);
	}
	
	@GET
	@Path("/{id}/Delete")
	public response Delete(@PathParam("id") int id)
	{
		return DataAccess.deleteNotification(id);
	}
	
	
	
}
