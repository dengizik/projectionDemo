package com.lbs.data.demo.topic.config;

import com.lbs.base.config.logger.LbsLogger;
import com.lbs.data.demo.topic.controller.TopicController;
import com.lbs.data.demo.topic.model.ProjectionWithSpecification;
import com.lbs.data.demo.topic.model.entity.Topic;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.matchprocessor.SubclassMatchProcessor;
import io.github.lukehutch.fastclasspathscanner.scanner.ClassInfo;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import oracle.jrockit.jfr.jdkevents.throwabletransform.ConstructorTracerWriter;
import org.slf4j.Logger;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.PropertiesConfigurationFactory;
import org.springframework.boot.env.PropertySourcesLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.PropertySources;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.persistence.Entity;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
public class ApplicationConfiguration implements ResourceLoaderAware {

    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    private final Logger logger = LbsLogger.getLogger(ApplicationConfiguration.class);

    @Autowired
    private ApplicationContext appContext;

    AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();

    HashMap<String, String> bufferHM = new HashMap<>();

    @Bean
    public RestEndPointConfig searchConfig() throws ClassNotFoundException {//TODO: annotation scan yapacaksın
        //TODO: scan all entity package and find interfaces
        final ScanResult scanResult = new FastClasspathScanner(new String[]{ "com.lbs.data.demo.topic.model" }) //to blacklist: "-com.lbs.data.demo.topic.model.adapter", );
                .matchClassesWithAnnotation(ProjectionWithSpecification.class,
                // scans package with annotated with ProjectionWithSpecification
                c -> System.out.println("Found ProjectionWithSpecification annotation on class: "
                        + c.getName())).scan();

        List<String> classesWithAnnotationProjectWSpec = scanResult.getNamesOfClassesWithAnnotation(ProjectionWithSpecification.class);

        //TODO: read the value of the ProjectionWithSpecification annotation's value on found entity
        //in order to get the value of annotation first you have to instantiate the object
        //Since the class is not known at compile time the only way to access it is by reflection.

        for (String aClassWithAnnotationProjectWSpec: classesWithAnnotationProjectWSpec){
            Class<?> entityClass = Class.forName(aClassWithAnnotationProjectWSpec);
            try{
                Constructor<?> constructor = entityClass.getConstructor();
                Object object = constructor.newInstance(); //safer approach: https://stackoverflow.com/questions/9886266/is-there-a-way-to-instantiate-a-class-by-name-in-java
                ProjectionWithSpecification pr = object.getClass().getAnnotation(ProjectionWithSpecification.class);
                pr.projector();
                logger.info(pr.projector());

                bufferHM.put(aClassWithAnnotationProjectWSpec,pr.projector());
               //bufferHM'i application context'a koymaya çalışıyorum

            }catch (Exception e){
                logger.error(e.getLocalizedMessage());
            }
        }



        //TODO: context'ten bu map'iokuyup search result'ta dönmen lazım

        //Map <class name, interface name>
        //get the value of the annotation of given class
        //elimde class name var String olarak, annotation name de var, o annotation'ın içindeki
        // get the annotation of a given class name (String)


        /*// Only scan classpath for this one class
        new FastClasspathScanner("com.xyz.MyWhitelistedClass")
        // Whitelist a package, and one class in a different package
        new FastClasspathScanner("com.pqr", "com.xyz.MyWhitelistedClass")
        // Whitelist a package, and then blacklist one class in that package
        new FastClasspathScanner("com.xyz", "-com.xyz.MyBlacklistedClass")
*/

//        Annotation annotation = obj.getAnnotation(c.g.class);
//        TesterInfo testerInfo = (TesterInfo) annotation;

        return bindPropertiesToTarget(RestEndPointConfig.class, null, "classpath:/config/search/search-config.yml");
    }

    @Bean("annotatedClass")
    public HashMap<String, String> getString() {
        return bufferHM;
    }

    private <T> T bindPropertiesToTarget(Class<T> clazz, String prefix, String... locations) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            T newInstance = constructor.newInstance();

            PropertiesConfigurationFactory<Object> factory = new PropertiesConfigurationFactory<>(newInstance);
            factory.setPropertySources(loadPropertySources(locations));
            factory.setConversionService(new DefaultConversionService());
            if (prefix != null && prefix.trim().length() > 0) {
                factory.setTargetName(prefix.trim());
            }
            factory.bindPropertiesToTarget();
            return newInstance;

        }
        catch (Exception ex) {
            String targetClass = ClassUtils.getShortName(clazz);
            throw new BeanCreationException(clazz.getSimpleName(),
                    "Could not bind properties to " + targetClass + " (" + clazz.getSimpleName() + ")", ex);
        }
    }

    private PropertySources loadPropertySources(String[] locations) {
        try {
            PropertySourcesLoader loader = new PropertySourcesLoader();
            for (String location : locations) {
                Resource resource = this.resourceLoader.getResource(location);
                loader.load(resource);
            }
            return loader.getPropertySources();
        }
        catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
