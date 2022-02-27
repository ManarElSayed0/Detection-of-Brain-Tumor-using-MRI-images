        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tumor.classification;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author dell
 */
public class IMAGE {
    public void PNGToJPG(File PNGImage, File JPGImage) {
         try{
            BufferedImage png=ImageIO.read(PNGImage);
            BufferedImage jpg=new BufferedImage(png.getWidth(),png.getHeight(),BufferedImage.TYPE_INT_RGB);  
               
            jpg.createGraphics().drawImage(png,0,0,null);
            ImageIO.write(jpg, "JPG", JPGImage);
            
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    
    public void GrayScaling(File input, File output) {
         try{
             ImageInputStream iis=ImageIO.createImageInputStream(input);
             Iterator<ImageReader> it=ImageIO.getImageReaders(iis);
             ImageReader ir=it.next();
             String ImageFormat=ir.getFormatName();
             
             BufferedImage image=ImageIO.read(iis);
             int width=image.getWidth();
             int height=image.getHeight();
             
             for(int y=0;y<height;++y){
                 for(int x=0;x<width;++x){
                     Color c=new Color(image.getRGB(x, y));
                     int red=(int)(c.getRed()*0.2126);
                     int green=(int)(c.getGreen()*0.7152);
                     int blue=(int)(c.getBlue()*0.0722);
                     int sum=red+green+blue;
                     Color shadeOfGray=new Color(sum,sum,sum);
                     image.setRGB(x, y, shadeOfGray.getRGB());
                 }
             }
             ImageIO.write(image, ImageFormat, output);
         }
         catch(IOException ex){
             ex.printStackTrace();
         }
    }



 public void Resizing(File Original, File Resized, int Width, int Height, String Format) {
         //Java IO Exception
        try{
           BufferedImage O=ImageIO.read(Original);
           BufferedImage R=new BufferedImage(Width,Height,O.getType());
           Graphics2D g2=R.createGraphics();
           g2.drawImage(O,0,0,Width,Height,null);
           g2.dispose();
           ImageIO.write(R, Format, Resized);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
}
