package compile;

import compile.support.CompiledClassLoader;
import compile.support.ComplieHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        StringBuffer program = new StringBuffer();
        program.append("public class HelloWorld {                 \n");
        program.append("	public static void main(String[] args) { \n");
        program.append("		System.out.println(\"HelloWorld\");  \n");
        program.append("	}                                        \n");
        program.append("}                                         ");
        CompiledClassLoader classLoader = ComplieHelper.compile("HelloWorld", program.toString());
        Class<?> clazz = classLoader.loadClass("HelloWorld");
        Method main = clazz.getMethod("main", String[].class);
        main.invoke(null, new Object[]{null});
    }
}
