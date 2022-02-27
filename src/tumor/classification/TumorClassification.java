/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tumor.classification;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author dell
 */
public class TumorClassification {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
      /*IMAGE I=new IMAGE();      
      File f1=new File("C:\\Users\\ghada\\Desktop\\tumor\\7.JPG"); 
    
     File resized=new File("C:\\Users\\ghada\\Desktop\\70.JPG");
      I.Resizing(f1,resized,200,200,"JPG");*/
       /* File f2=new File("C:\\Users\\ghada\\Desktop\\500.JPG");
      I.PNGToJPG(f1, f2);*/
      File output=new File("C:\\Users\\ghada\\Desktop\\scaled\\77.JPG");
      //I.GrayScaling(resized,output);
      
     
      
    //File f=new File("C:\\Users\\ghada\\Desktop\\Scaled\\66.JPG");
      FeatureExtraction FE=new feature(output);
      FE.calc();
      double []arr=new double[5];
      arr[0]=FE.getMean();
      arr[1]=FE.getVar();
      arr[2]=FE.getR();
      arr[3]=FE.getSkew();
      arr[4]=FE.getKur();     
   
     BayseianClassifier bayes=new BayseianClassifier("C:\\Users\\ghada\\Documents\\"
              + "NetBeansProjects\\Tumor Classification\\Features.txt");
       
   int pre=bayes.Predict(arr);
     if(pre==0){
         System.out.println("Normal");
     }
     else{
         System.out.println("Tumor");  
          }
     
     double accuracy=bayes.accuracy();
       System.out.printf(("Accuracy: %.2f \n"),accuracy);
    }
    }
    

    

