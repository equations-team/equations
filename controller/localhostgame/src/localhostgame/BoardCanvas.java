package localhostgame;

import java.awt.*;

@SuppressWarnings("serial")
public class BoardCanvas extends Canvas
{
	private Image myImage;
    private String myValue;
    
    public BoardCanvas(String value)
    {
        myImage = null;
        myValue = value;
    }
    
    public BoardCanvas(Image image)
    {
        myImage = image;
        myValue = null;
    }
    
    public void paint(Graphics g)
    {
        if(myValue==null)
        {
            g.drawImage(myImage,0,0,this);
        }
        else
        {
            g.setColor(Color.red);
            g.drawString(myValue,20,20);
        }
    }
    
    public void setImage(Image data)
    {
      myImage = data;
      this.repaint();
    }
}
