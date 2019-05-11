package com.kylodw.pay.compiler;

import com.google.auto.service.AutoService;
import com.kylodw.annotains.WXPayEntry;

import java.lang.annotation.Annotation;
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
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/11
 */
@AutoService(Processor.class)
public class WXPayProcessor extends AbstractProcessor {
    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        mFiler = processingEnvironment.getFiler();
        super.init(processingEnvironment);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

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
        annotations.add(WXPayEntry.class);
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println("----------------------------------------------");
        System.out.println("----------------------------------------------");
        System.out.println("----------------------------------------------");
        generateWWPayCode(roundEnvironment);
        return false;
    }

    private void generateWWPayCode(RoundEnvironment roundEnvironment) {
        WXPayEntryVisitor wxPayEntryVisitor = new WXPayEntryVisitor();
        wxPayEntryVisitor.setFiler(mFiler);
        scaElement(roundEnvironment, WXPayEntry.class, wxPayEntryVisitor);

    }

    private void scaElement(RoundEnvironment roundEnvironment, Class<? extends Annotation> wxPayEntryClass, AnnotationValueVisitor wxPayEntryVisitor) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(wxPayEntryClass);
        for (Element element : elements) {
            System.out.println("***"+element.toString());
            List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();
            for (AnnotationMirror annotationMirror : annotationMirrors) {
                System.out.println("***"+annotationMirror.toString());
                Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues = annotationMirror.getElementValues();
                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues.entrySet()) {
                    System.out.println("***value:"+ entry.getValue());
                    entry.getValue().accept(wxPayEntryVisitor, null);
                }
            }
        }
    }
}
