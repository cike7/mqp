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
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;


/**
 * Copyright (C) 王字旁的理
 * Date: 9/6/2021
 * Description: 注释编译器
 * Author: zl
 */
@AutoService(Processor.class)
public class ArrayAutoAddCompiler extends AbstractProcessor {

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
        types.add(AutoGetTargetArray.class.getCanonicalName());
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

        //每个class对应的元素
        Map<String, List<VariableElement>> map = new HashMap<>();
        //获取类中所有的需要处理的注解
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(AutoGetTargetArray.class);

        for (Element element : elements) {
            //每个注解中的字段
            VariableElement variableElement = (VariableElement) element;
            //class名字
            String ClassNameKey = variableElement.getEnclosingElement().getSimpleName().toString();
            //获取class里面的元素
            List<VariableElement> variableElements = map.get(ClassNameKey);
            if (variableElements == null) {
                variableElements = new ArrayList<>();
                //加入activity和对于的元素
                map.put(ClassNameKey, variableElements);
            }
            variableElements.add(variableElement);
        }

        if (map.size() > 0) {
            //写入流
            Writer writer = null;
            //获取类中所有的注解
            Set<? extends Element> classElements = roundEnvironment.getElementsAnnotatedWith(AutoSetTarget.class);

            for (String className : map.keySet()) {
                //获取当前注解类的所有字段
                List<VariableElement> variableElements = map.get(className);
                //获取注解的包名
                String packageName = processingEnv.getElementUtils().getPackageOf(variableElements.get(0).getEnclosingElement()).toString();

                try {
                    //创建一个.java文件
                    JavaFileObject sourceFile = filer.createSourceFile(packageName + "." + className + "_AutoArray");
                    //打开写入流
                    writer = sourceFile.openWriter();
                    //package com.zhu.mqp;
                    writer.write("package " + packageName + ";\n");
                    //import com.zhu.mqp.MainActivity;
                    writer.write("import " + packageName + "." + className + ";\n");
                    //import com.zhu.processortest.IBinder;
                    writer.write("import " + IBinder.class.getCanonicalName() + ";\n");
                    //public class MainActivity_ViewBinding implements IBinder<MainActivity>{
                    writer.write("public class " + className + "_AutoArray implements IBinder<" + className + ">{\n");
                    //@Overridev
                    writer.write("@Override\n");
                    //public void bind(MainActivity target){
                    writer.write("public void bind(" + className + " target){\n");
                    //遍历注解所有字段
                    for (VariableElement variableElement : variableElements) {
                        //便利所有注解类
                        for (Element element : classElements) {
                            //注解字段的类型
                            int fieldType = variableElement.getAnnotation(AutoGetTargetArray.class).value();
                            //注解类的类型
                            int classType = element.getAnnotation(AutoSetTarget.class).value();
                            //判断类型是否一致
                            if (fieldType == classType) {
                                writer.write("target." + variableElement + ".add(new " + element + "());\n");
                            }
                        }
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
