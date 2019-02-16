package compile.support;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.util.Arrays;

public class ComplieHelper {
    public static CompiledClassLoader compile(String className, String program) {
        JavaFileObject file = new JavaSourceFromString(className, program);
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        SimpleJavaFileManager fileManager = new SimpleJavaFileManager(compiler.getStandardFileManager(null, null, null));
        CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
        boolean success = task.call();
        if (success) {
            return new CompiledClassLoader(fileManager.getGeneratedOutputFiles());
        } else {
            return null;
        }
//		System.out.println("Success: " + success);
//		if (success) {
//			CompiledClassLoader classLoader = new CompiledClassLoader(fileManager.getGeneratedOutputFiles());
//			Class<?> clazz;
//			try {
//				clazz = classLoader.loadClass("HelloWorld");
//				Method main = clazz.getMethod("main", String[].class);
//				main.invoke(null, new Object[] { null });
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (NoSuchMethodException e) {
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (InvocationTargetException e) {
//				e.printStackTrace();
//			}
//		}
    }
}
