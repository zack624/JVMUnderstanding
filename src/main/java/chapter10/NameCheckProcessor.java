package chapter10;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * 插入式注解处理器，对源码的命名风格作校验
 * javac -encoding UTF-8 .\chapter10\NameChecker.java
 * javac -encoding UTF-8 .\chapter10\NameCheckProcessor.java
 * javac -encoding UTF-8 -processor chapter10.NameCheckProcessor .\chapter10\BADLY_NAMED_CODE.java
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor {
    private NameChecker nChecker;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nChecker = new NameChecker(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()){
            for (Element elem: roundEnv.getRootElements()){
                nChecker.checkNames(elem);
            }
        }
        return false;
    }
}
