
package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pc
 */
@XmlRootElement (name="notification")
public class notification {
    private int id;
    private int num_placeHolder;
    private String content;
    private String subject;
    private String language;
    
    
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
	public int getNum_placeHolder() {
		return num_placeHolder;
	}
	public void setNum_placeHolder(int num_placeHolder) {
		this.num_placeHolder = num_placeHolder;
	}
	
    
}
