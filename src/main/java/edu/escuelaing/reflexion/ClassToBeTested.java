package edu.escuelaing.reflexion;

import edu.escuelaing.reflexion.Test;

public class ClassToBeTested {
    @Test
    public static void m1(){
        System.out.println("Ok.");
    }

    public static void m2(){
        System.out.println("Ok.");
    }

    public static void m3(){
        System.out.println("Ok.");
    }

    @Test
    public static void m4() throws Exception{
        throw new Exception ("Error for m4");
    }

    @Test
    public static void m5() throws Exception{
        throw new Exception ("Error for m5");

    }
}