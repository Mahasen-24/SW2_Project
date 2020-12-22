
package infrastructure;

import model.notification;
import model.response;

/**
 *
 * @author pc
 */
public interface INotification_T_DataAccess {
    
    public notification ReadNotification(int id);
    
    public response CreateNotification(notification n);
    
    public response UpdateNotification(int id,notification n);
    
    public response deleteNotification(int id);
    
    
}
