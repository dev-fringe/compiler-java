package compile.support;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class SimpleJavaFileManager extends ForwardingJavaFileManager {
    private final List<ClassJavaFileObject> outputFiles;

    @SuppressWarnings("unchecked")
    public SimpleJavaFileManager(JavaFileManager fileManager) {
        super(fileManager);
        outputFiles = new ArrayList<ClassJavaFileObject>();
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind,
                                               FileObject sibling) {
        ClassJavaFileObject file = new ClassJavaFileObject(className, kind);
        outputFiles.add(file);
        return file;
    }

    public List<ClassJavaFileObject> getGeneratedOutputFiles() {
        return outputFiles;
    }
}