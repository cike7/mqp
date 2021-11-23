package com.zhu.processortest;


import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;


/**
 * Copyright (C) 王字旁的理
 * Date: 9/6/2021
 * Description: 注释编译器
 * Author: zl
 */
@AutoService(Processor.class)
public class AnnotationsCompiler extends AbstractProcessor {

    /**
     * 需要用来生成文件的对象
     */
    private Filer filer;

    private Elements elementUtils;

    /**
     * 获取支持的源版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }

    /**
     * 获取支持的注释类型
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //将所需支持的类型添加进集合
        Set<String> types = new HashSet<>();
        types.add(BindView.class.getCanonicalName());
        return types;
    }


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        // 1.获取类
        // 2.获取方法
        // 3.获取带有注解的类和类中的注解的元素
        // 4.分别为每个不同的类创建.java文件
        // 5.根据前面获取到的元素组装整一个类写入.java文件

        //获取类中所有的BindView注解
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        //每个class对应的元素
        Map<String, List<VariableElement>> map = new HashMap<>();

        for (Element element : elements) {
            //每个注解中的class
            VariableElement variableElement = (VariableElement) element;
            //class名字
            String activityName = variableElement.getEnclosingElement().getSimpleName().toString();
            //获取class里面的元素
            List<VariableElement> variableElements = map.get(activityName);

            if (variableElements == null) {
                variableElements = new ArrayList<>();
                //加入activity和对于的元素
                map.put(activityName, variableElements);
            }
            variableElements.add(variableElement);
        }

        if (map.size() > 0) {
            Writer writer = null;

            for (String activityName : map.keySet()) {

                List<VariableElement> variableElements = map.get(activityName);

                Element enclosingElement = (variableElements.get(0).getEnclosingElement());

                String packageName = processingEnv.getElementUtils().getPackageOf(enclosingElement).toString();

                try {
                    //创建一个.java文件
                    JavaFileObject sourceFile = filer.createSourceFile(packageName + "." + activityName + "_ViewBinding");
                    //打开写入流
                    writer = sourceFile.openWriter();
                    //package com.zhu.mqp;
                    writer.write("package " + packageName + ";\n");
                    //import com.zhu.mqp.MainActivity;
                    writer.write("import " + packageName + "." + activityName + ";\n");
                    //import com.zhu.processortest.IBinder;
                    writer.write("import " + IBinder.class.getCanonicalName() + ";\n");
                    //public class MainActivity_ViewBinding implements IBinder<MainActivity>{
                    writer.write("public class " + activityName + "_ViewBinding implements IBinder<" + activityName + ">{\n");
                    //@Overridev
                    writer.write("@Override\n");
                    //public void bind(MainActivity target){
                    writer.write("public void bind(" + activityName + " target){\n");

                    for (VariableElement variableElement : variableElements) {

                        String variableName = variableElement.getSimpleName().toString();

                        int id = variableElement.getAnnotation(BindView.class).value();

                        TypeMirror typeMirror = variableElement.asType();
                        //target.textView = (android.widget.TextView)target.findViewById(2131296640);
                        writer.write("target." + variableName + " = (" + typeMirror + ")target.findViewById(" + id + ");\n");

                    }

                    writer.write("}\n}\n");

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }

        return true;

    }


}
