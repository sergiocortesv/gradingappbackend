package com.k1687.leisure.grading.configuration;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.stream.Collectors;

public class ORMUtil {

    public static <T> List<T> initializeAndUnproxyList(java.util.List<T> entities){
        return entities.stream().map(obj ->ORMUtil.unproxy(obj)).collect(Collectors.toList());
    }

    public static <T> T intializeAndUnproxy(T entity){
        System.out.println("Entity Class" + entity.getClass());
        if(entity == null){
            return null;
        }

        ;
        entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
        return entity;
    }

    public static<T> T unproxy(T entity){
        return (T) Hibernate.unproxy(entity);
    }

}
