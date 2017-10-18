package com.lbs.data.demo.topic;

//Marker Annotation
//Single Value Annotation
//Mutli Value Annotation

import java.lang.annotation.*;

//Meta Annotation
//@Inherited //
//@Documented //after creating the software if you want your annotation to be placed in javadocs
@Target(ElementType.TYPE) //at what level we'll be using this annotation: class, method, field, constructor
@Retention(RetentionPolicy.RUNTIME) //till what point you want this annotation Source (it won't be available on the compiled file) Runtime (it will be available at runtime also)
@interface ProjectionWithSpecification {
    String interfaceClass();
}


