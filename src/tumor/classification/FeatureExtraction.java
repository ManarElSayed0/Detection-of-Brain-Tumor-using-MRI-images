/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tumor.classification;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author dell
 */

//abstraction
//Encapsulation
public abstract class FeatureExtraction {
    private double mean;
    private double var;
    private double skew;
    private double R;
    private double Kur;
    protected ImageInputStream iis;
    protected Iterator<ImageReader> it;
    protected ImageReader ir;
    protected String ImageFormat;
    protected BufferedImage img;
    protected double pixel;
    protected Raster raster;

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getVar() {
        return var;
    }

    public void setVar(double var) {
        this.var = var;
    }

    public double getSkew() {
        return skew;
    }

    public void setSkew(double skew) {
        this.skew = skew;
    }

    public double getR() {
        return R;
    }

    public void setR(double R) {
        this.R = R;
    }

    public double getKur() {
        return Kur;
    }

    public void setKur(double Kur) {
        this.Kur = Kur;
    }
    
       
    
    
  public  FeatureExtraction()
  {
       this.mean=this.var=this.skew=this.pixel=this.R=this.Kur=0;
  }
    public  FeatureExtraction(File F)
    {
        
        this.mean=this.var=this.skew=this.pixel=this.R=this.Kur=0;
        try{
             this.iis=ImageIO.createImageInputStream(F);
           this. it=ImageIO.getImageReaders(iis);
        this.ir=it.next();
          this.  ImageFormat=ir.getFormatName();
           this. img=ImageIO.read(iis);
          this. raster = img.getRaster();
              }  
     catch(IOException ex){
         ex.printStackTrace();
     }   
        }
    public void Mean()
    {
        double sum=0;
          for (int y = 0; y <img.getWidth(); ++y) {
               for (int x = 0; x <img.getHeight(); ++x) {  
                   pixel=raster.getSample(x,y,0);
                  
                   sum+=pixel;
                   }
               }
          setMean(sum/(img.getWidth()*img.getHeight()));
         // mean=sum/(img.getWidth()*img.getHeight());
        //return mean;
        //getMean();
    }
    public void varience()
    {
        double s=0;
         for (int y = 0; y <img.getWidth(); ++y) {
                for (int x = 0; x <img.getHeight(); ++x) {  
                    pixel=raster.getSample(x,y,0);
                    s+=((pixel-mean)*(pixel-mean));
                    }
         }
         setVar(s/(img.getWidth()*img.getHeight()));
         //var=s/(img.getWidth()*img.getHeight());
         //return var;
         //getVar();
    }
    public void Smoothness()
    {
        varience();
          for (int y = 0; y <img.getWidth(); ++y) {
             for (int x = 0; x <img.getHeight(); ++x) {                
               //pixel=raster.getSample(x,y,0);
                }             
             
          }
          setR(1-(1/(1+var)));
         // R=1-(1/(1+var));
          //return R;
         // getR();
    }
    public void Skewness()
    {
        double M=0;
         for (int y = 0; y <img.getWidth(); ++y) {
             for (int x = 0; x <img.getHeight(); ++x) {  
                 pixel=raster.getSample(x,y,0);
                 M+=((pixel-mean)*(pixel-mean)*(pixel-mean));                 
             }
         }
         setSkew(M/((img.getWidth()*img.getHeight())*(var*Math.sqrt(var))));
          //skew=M/((img.getWidth()*img.getHeight())*(var*Math.sqrt(var)));
          //return skew;
          //getSkew();
    }
    public void Kurtosis()
    {
        double K=0;
         for (int y = 0; y <img.getWidth(); ++y) {
             for (int x = 0; x <img.getHeight(); ++x) {  
                 pixel=raster.getSample(x,y,0);
                 K+=((pixel-mean)*(pixel-mean)*(pixel-mean)*(pixel-mean));
         }
         }
         setKur((K/((img.getWidth()*img.getHeight())*var*var))-3);
         // Kur=(K/((img.getWidth()*img.getHeight())*var*var))-3;
          //return Kur;
          //getKur();
    }

    public abstract void calc();

    public final void create()
    {
          
             try{
               
            File file = new File ("Features.txt");
            FileWriter myWriter = new FileWriter(file,true);  
            myWriter.write(mean+"@"+var+"@"+R+"@"+skew+"@"+Kur+"\n");         
             myWriter.close();
              }  
     catch(IOException ex){
         ex.printStackTrace();
     }  

    }

}
