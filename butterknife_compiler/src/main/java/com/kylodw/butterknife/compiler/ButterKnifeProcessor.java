package com.kylodw.butterknife.compiler;

import com.butterknife.annotation.KBindView;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
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
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/10
 */
@AutoService(Processor.class)
public class ButterKnifeProcessor extends AbstractProcessor {
    private Filer mFiler;
    private Elements mElements;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnv.getFiler();
        mElements = processingEnv.getElementUtils();
    }

    /**
     * 指定处理的版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    /**
     * 需要处理的注解
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : getSupportedAnnotations()) {
            types.add(annotation.getCanonicalName());
        }
//        types.add(KBindView.class.getCanonicalName());
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(KBindView.class);
        return annotations;
    }

    /**
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println("**************************进入");
        System.out.println("**************************进入");
        System.out.println("**************************进入");
        System.out.println("**************************进入");
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(KBindView.class);

        Map<Element, List<Element>> elementListMap = new LinkedHashMap<>();
        for (Element element : elements) {
            System.out.println(element.toString());
            Element enclosingElement = element.getEnclosingElement();
            System.out.println(enclosingElement.toString());
            List<Element> bindViews = elementListMap.get(enclosingElement);
            if (bindViews == null) {
                bindViews = new ArrayList<>();
                elementListMap.put(enclosingElement, bindViews);
            }
            bindViews.add(element);
        }
        for (Map.Entry<Element, List<Element>> entrySet : elementListMap.entrySet()) {
            Element encloseElement = entrySet.getKey();
            List<Element> bindViews = entrySet.getValue();
            //com......MainActivity
            String activityClassName = encloseElement.getSimpleName().toString();
            //MainActivity
            ClassName bestGuessClassName = ClassName.bestGuess(activityClassName);
            ClassName unbindClassName = ClassName.get("com.butterknife", "Unbinder");
            //public final class MainActivity_ViewBiding  implements Unbinder
            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(
                    activityClassName + "_ViewBinding")
                    .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                    .addSuperinterface(unbindClassName)
                    .addField(bestGuessClassName, "target", Modifier.PRIVATE);
            //实现接口的方法
            ClassName callSupeClassName = ClassName.get("android.support.annotation", "CallSuper");
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("unbind")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addAnnotation(callSupeClassName);
            //构造函数
            MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
                    .addParameter(bestGuessClassName, "target");
            constructorBuilder.addStatement("this.target=target");


            //生成属性 findViewById
            for (Element bindView : bindViews) {
                String filedName = bindView.getSimpleName().toString();
                System.out.println(filedName);
                ClassName utilsClassName = ClassName.get("com.butterknife", "Utils");
                System.out.println(utilsClassName.simpleName());
                System.out.println(filedName);
                int resId = bindView.getAnnotation(KBindView.class).value();
                //添加属性
                constructorBuilder.addStatement("target.$L=$T.findViewById(target,$L)", filedName, utilsClassName, resId);

                methodBuilder.addStatement("target.$L=null", filedName);
            }
            //将实现的接口方法添加进class中
            classBuilder.addMethod(methodBuilder.build());
            classBuilder.addMethod(constructorBuilder.build());
            try {
                String packageName = mElements.getPackageOf(encloseElement).getQualifiedName().toString();
                JavaFile.builder(packageName, classBuilder.build())
                        .build().writeTo(mFiler);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("进入异常");
            }

        }
        return false;
    }
}
