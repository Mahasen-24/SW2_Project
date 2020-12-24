package infrastructure;

import java.util.HashMap;
import java.util.Map;
import model.notification;
import model.response;



public class MemoryNotification_T_DataAccess implements INotification_T_DataAccess{
 
	private static Map<Integer,notification> templetes=new HashMap<Integer,notification>();

	@Override
	public notification ReadNotification(int id) {
		
		return templetes.get(id);
	}

	@Override
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
	public response UpdateNotification(int id,notification n) {
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
	public response deleteNotification(int id) {
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
