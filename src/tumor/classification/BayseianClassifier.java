/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tumor.classification;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author Amro
 */
//polymorphism
public class BayseianClassifier implements classifier{
    
 
    public BayseianClassifier(String filePath) throws FileNotFoundException {
     
        
        Scanner file;
       file=new Scanner(new File(filePath));
       for(int i=0;i<dataMatrix.length;i++)
       {
           String[] line=file.nextLine().split("@");
           for(int j=0;j<line.length-1;j++)
           {
            dataMatrix[i][j]=Double.parseDouble(line[j]);
           }
           labels[i]=Integer.parseInt(line[line.length-1]);
           if(labels[i]==0)
               classes[0]++;
           else
               classes[1]++;
       }
       calculateMean();
       calculateStd();
   
       Pwi[0]=Double.valueOf(classes[0])/Double.valueOf((classes[0]+classes[1]));
       Pwi[1]=Double.valueOf(classes[1])/Double.valueOf((classes[0]+classes[1]));
    }
    @Override
    public void calculateMean()
    {
        double sum0=0,sum1=0;
        for(int i=0;i<mean[0].length;i++)
        {
            sum0=0;sum1=0;
            for(int j=0;j<dataMatrix.length;j++)
            {
                if(labels[j]==0)
                    sum0+=dataMatrix[j][i];
                else
                    sum1+=dataMatrix[j][i];
                    
            }
            mean[0][i]=sum0/Double.valueOf(classes[0]);
            mean[1][i]=sum1/Double.valueOf(classes[1]);
        }
    }
    @Override
    public void calculateStd()
    {
        double sum0=0,sum1=0;
        for(int i=0;i<std[0].length;i++)
        {
            sum0=0;sum1=0;
            for(int j=0;j<dataMatrix.length;j++)
            {
                
                if(labels[j]==0)
                    sum0+=Math.pow((dataMatrix[j][i]-mean[0][i]),2);
                else
                    sum1+=Math.pow((dataMatrix[j][i]-mean[1][i]),2);
            }
            var[0][i]=sum0/Double.valueOf(classes[0]);
            var[1][i]=sum1/Double.valueOf(classes[1]);
            std[0][i]=Math.sqrt(var[0][i]);
            std[1][i]=Math.sqrt(var[1][i]);
        }
    }
    @Override
   public int Predict(double X[])
   {
      double Px=0;
      double Px_givenYi[]={1,1};
      double x[][]=new double[2][X.length];
      for(int i=0;i<X.length;i++)
      {
          x[0][i]=PXj_givenYi(X[i], mean[0][i], std[0][i], var[0][i]);
          x[1][i]=PXj_givenYi(X[i], mean[1][i], std[1][i], var[1][i]);
          
      }
      for(int i=0;i<X.length;i++)
      {
          Px_givenYi[0]=Px_givenYi[0]*x[0][i];
          Px_givenYi[1]=Px_givenYi[1]*x[1][i];
          
      }
      Px=(Px_givenYi[0]*Pwi[0])+(Px_givenYi[1]*Pwi[1]);
      double Pw0=Px_givenYi[0]*Pwi[0]/Px;
      double Pw1=Px_givenYi[1]*Pwi[1]/Px;
      if(Pw1>Pw0)
          return 1;
      return 0; 
   }
   @Override
   public double PXj_givenYi(double x,double mean , double std,double var)
   {    
       double denominator=Math.sqrt(2.0*Math.PI)*std;
       double e_power=-0.5*Math.pow((x-mean),2)/var;
       double nominator=Math.pow(Math.E,e_power);
       return nominator/denominator;
   }
   @Override
   public double accuracy()
   {
       double acc=0;
       for(int i=0;i<dataMatrix.length;i++)
       {
           int prediction=Predict(dataMatrix[i]);
           if(prediction==labels[i])
               acc++;
               
       }
       return acc/Double.valueOf(dataMatrix.length)*100;
   }

   
    public static double[][] getDataMatrix() {
        return dataMatrix;
    }

    public static int[] getLabels() {
        return labels;
    }

    public static double[][] getMean() {
        return mean;
    }

    public static double[][] getStd() {
        return std;
    }

    public static double[][] getVar() {
        return var;
    }

    public static int[] getClasses() {
        return classes;
    }

    public static double[] getPwi() {
        return Pwi;
    }
}
