package edu.escuelaing.reflexion;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Executor {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class c = Class.forName(args[0]);
        Class[] mainParamTypes = {String[].class};
        Method main = c.getDeclaredMethod("main", mainParamTypes);

        String[] parms = {args[1],args[2]};
        main.invoke(null,(Object)parms);

    }
}