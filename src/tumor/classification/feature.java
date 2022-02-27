/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tumor.classification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author dell
 */

//inheritance
public class feature  extends FeatureExtraction{
    
    public feature(File F) {
        super(F);
    }
    @Override
    public void calc() {
     Mean();
     varience();
     Skewness();
     Smoothness();
     Kurtosis();
    // create();
     
    }
}
