package com.lbs.data.demo;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class UberClass {

    //bi paketteki bütün class'ları scan edip (component scan gibi) onların interface'lerini oluşturma olabilir
// ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfig.class);

    //entity'ler kendilerini işaretlese projection'ı olacak mı olmayacak mı diye (@DenizAnnotation)
    //işaretli entity'ler servis katmanında kendilerini registerme metoduyla register etse
    // scan edilip repo'ları üzerinden simple versiyonlarına ulaşılsa

    HashMap registeringHashMap = new HashMap();//global olsun
       // hashMap

// 1. Base Interface veya abstract class yap bir tane içinde RegisterMe metodu olsun
// 2. hashtable'a  bu entity'ye karşılık interface budur diyecek
// 3. a'ya karşılık şu interface var diyebilecek
// 4. annotation scan yap, ben şu ent 'nin şu interface'i olarak ortadayım diyorum

//    ayağa kalkarken bu interface'i kim kullanıyor biliyor olacak
//farklı farklı projection yapabilme ihtimali var mı bak bakalım (zorunlu değil)
//
//
//    findByProjectionID diyip restcontroller'ın içinde tanımlayabilirsin

    public void registerMe(Class className, Class interfaceName){
        registeringHashMap.put("class", interfaceName);

    }

}
