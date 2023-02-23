package pl.edu.pw.mini.jrafalko.main;

import pl.edu.pw.mini.jrafalko.censor.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Cenzor implements Censorable {
    @Override
    public List<Pracownik> cenzuruj(List<Pracownik> list) {
        // todo
        for(Pracownik pracownik : list){
            stringiSekretarka(pracownik);
            createShortcut(pracownik);
            replaceString(pracownik);
            replaceInt(pracownik);
            runImmediately(pracownik);
            //replaceIntEnum(pracownik);
            //list = deleteWorker(pracownik, list);
        }

        return list;
    }

    public static void stringiSekretarka(Pracownik pracownik){
        if(pracownik.getClass().isAnnotationPresent(EmptyString.class)){
            Class<?> current = pracownik.getClass();
            while(current.getSuperclass()!=null){ // we don't want to process Object.class
                // do something with current's fields
                Field[] fields = current.getDeclaredFields();
                for (Field field: fields) {
                    if (field.getType().isAssignableFrom(String.class)){
                        System.out.println(field);
                        field.setAccessible(true);
                        try {
                            field.set(pracownik, " ");
                        } catch (IllegalAccessException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }
                current = current.getSuperclass();
            }
        }
    }

    public static void createShortcut(Pracownik pracownik){
        Field[] fields = pracownik.getClass().getDeclaredFields();
        for (Field field: fields) {
            if(field.isAnnotationPresent(Shortcut.class)){
                if (field.getType().isAssignableFrom(String.class)){
                    field.setAccessible(true);
                    try {
                        String firstThree = "";
                        if(field.get(pracownik).toString().length()>2){
                            firstThree = field.get(pracownik).toString().substring(0,3) + "___";
                        }else {
                            firstThree = field.get(pracownik).toString() + "___";
                        }
                        field.set(pracownik, firstThree);
                    } catch (IllegalAccessException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
    }

    public static void replaceString(Pracownik pracownik){
        Field[] fields = pracownik.getClass().getDeclaredFields();
        for (Field field:fields) {
            if(field.isAnnotationPresent(ReplaceString.class)){
                field.setAccessible(true);
                try {
                    ReplaceString customText = field.getAnnotation(ReplaceString.class);
                    System.out.println(customText + " !not sure about it");
                    field.set(pracownik, customText.text());
                }catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public static void replaceInt(Pracownik pracownik){
        Field[] fields = pracownik.getClass().getDeclaredFields();
        for (Field field:fields) {
            if(field.isAnnotationPresent(ReplaceInt.class)){
                field.setAccessible(true);
                if(!field.getType().equals(int.class)){
                    continue;
                }
                try {
                    field.set(pracownik, 0);
                } catch (IllegalAccessException e) {
                    System.err.println(e.getMessage());
                }

            }
        }
    }

    public static void runImmediately(Pracownik pracownik){
        Method[] methods = pracownik.getClass().getDeclaredMethods();
        for (Method method:methods) {
            if (method.isAnnotationPresent(RunImmediately.class)){
                RunImmediately annotation = method.getAnnotation(RunImmediately.class);
                for (int i = 0; i < annotation.times(); i++) {
                    method.setAccessible(true);
                    try {
                        method.invoke(pracownik);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
    }

    public static void replaceIntEnum(Pracownik pracownik){
        Field[] fields = pracownik.getClass().getDeclaredFields();
        for (Field field:fields) {
            if(field.isAnnotationPresent(ReplaceIntEnum.class)){
                field.setAccessible(true);
                ReplaceIntEnum annotation = field.getAnnotation(ReplaceIntEnum.class);
                try{
                    if(field.getType().equals(int.class)){
                        field.set(pracownik, annotation.replacementInt());
                    }else{
                        field.set(pracownik, annotation.replacementEnum());
                    }
                }catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public static List<Pracownik> deleteWorker(Pracownik pracownik, List<Pracownik> list){
        if(pracownik.getClass().isAnnotationPresent(DeleteWorker.class)){
            Class<?> current = pracownik.getClass();
            while(current.getSuperclass()!=null) { // we don't want to process Object.class
                // do something with current's fields
                try {
                    Field wiek = current.getDeclaredField("wiek");
                    wiek.setAccessible(true);
                    if ((Integer) wiek.get(pracownik) < 30) {
                        list.remove(pracownik);
                    }
                    return list;
                } catch (Exception e) {
                    e.printStackTrace();
                    current = current.getSuperclass();
                }
            }
        }
        return list;
    }


}
