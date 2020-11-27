
package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pc
 */
@XmlRootElement (name="notification")
public class notification {
    private int id;
    private String content;
    private String subject;
    private String language;
    private String channels;
    
    
    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return id;
    }
    public void setcontent(String con)
    {
        this.content=con;
    }
    public String getcontent()
    {
        return content;
    }
    
    public void setSubject(String sub)
    {
        this.subject=sub;
    }
    public String getSubject()
    {
        return subject;
    }
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getChannels() {
		return channels;
	}
	public void setChannels(String channels) {
		this.channels = channels;
	}
    
}
