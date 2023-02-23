package pl.edu.pw.mini.jrafalko.train;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // adnotacja jest przeznaczona dla klas'
@Retention(RetentionPolicy.RUNTIME)
public @interface TrolleyCompany {
    String nazwaProducenta = "NISSAN";
    String wielkoscDrezyny = "OGROMNA";
}
