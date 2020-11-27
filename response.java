
package model;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pc
 */
@XmlRootElement 
public class response {
    
    private boolean status;
    
    public void setStatus(boolean s)
    {
        status=s;
    }
    public boolean getStatus()
    {
        return status;
    }
    
}
