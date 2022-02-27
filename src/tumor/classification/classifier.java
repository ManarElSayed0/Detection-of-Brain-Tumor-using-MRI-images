/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tumor.classification;

/**
 *
 * @author dell
 */
public interface classifier {
    public static final double dataMatrix[][]=new double [11][5];
    public static final int labels[]=new int[11];
    public static  final double mean[][]=new double[2][5];
    public static  final double std[][]=new double[2][5];
    public static final double var[][]=new double[2][5];
    public static final int classes[]={0,0};
    public static final double Pwi[]=new double[2];
    public abstract  void calculateMean();
    public abstract   void calculateStd();
    public abstract int Predict(double X[]);
    public abstract double PXj_givenYi(double x,double mean , double std,double var);
    public  abstract double accuracy();
}
