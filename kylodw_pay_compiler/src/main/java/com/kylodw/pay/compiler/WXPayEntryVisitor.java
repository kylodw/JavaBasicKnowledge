package com.kylodw.pay.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor8;

/**
 * @Author kylodw
 * @Description:
 * @Date 2019/05/11
 */
public class WXPayEntryVisitor extends SimpleAnnotationValueVisitor8<Void, Void> {
    private String mPackageName;
    private TypeMirror mTypeMirror;
    private Filer mFiler;

    public void setFiler(Filer mFiler) {
        this.mFiler = mFiler;
    }

    @Override
    public Void visitString(String s, Void aVoid) {
        mPackageName = s;
        return aVoid;
    }

    @Override
    public Void visitType(TypeMirror typeMirror, Void aVoid) {
        mTypeMirror = typeMirror;
        generateWXPayCode();
        return aVoid;
    }


    private void generateWXPayCode() {
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder("WXPayEntryActivity")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror));//super
        try {
            JavaFile.builder(mPackageName + ".wxapi", classBuilder.build())
                    .addFileComment("微信支付自动生成").build().writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("进入异常");
        }
    }
}
