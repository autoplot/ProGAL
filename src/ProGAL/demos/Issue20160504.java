/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProGAL.demos;

import ProGAL.geom3d.PointWeighted;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This demonstrates a bug observed while integrating ProGal into Autoplot,
 * using ProGal to create a Tetrehedral mesh covering 3-d scatter data.
 * 
 * The first seed (6989262430066354096L) shows where the library is successful.
 * The second seed (4521225099312150406L) shows an input which causes the library
 * to get stuck, jumping back and forth between two tetrahedrons.
 * 
 * @author jbf
 */
public class Issue20160504 {
    public static void main( String[] ss ) {

        int n= 100;
        
        double[] xx= new double[n];
        double[] yy= new double[n];
        double[] zz= new double[n];
        
        long seed= 6989262430066354096L; // works
        //long seed= 4521225099312150406L; // fails
        
        Random rand= new Random(seed);
        
        for ( int i=0; i<n; i++ ) xx[i]= rand.nextDouble();
        for ( int i=0; i<n; i++ ) yy[i]= rand.nextDouble();
        for ( int i=0; i<n; i++ ) zz[i]= rand.nextDouble();
        
        List<ProGAL.geom3d.PointWeighted> points= new ArrayList(xx.length);
        for ( int i=0; i<xx.length; i++ ) {
            points.add( new PointWeighted( 
                xx[i], yy[i], zz[i], 1.0
            ) );
        }
        ProGAL.geom3d.tessellation.BowyerWatson.RegularTessellation rt= new ProGAL.geom3d.tessellation.BowyerWatson.RegularTessellation(points);
        
        System.err.println("rt: "+rt);
        System.err.println("rt.walk: "+rt.walk( new PointWeighted( 0.5, 0.5, 0.5, 1.0 ) ) );
    }
}
