//Esta class deixa de forma dinamina o update parcial, pois ela ira alterar apenas o que não esta como nulo, o restante ela copia como estava a informação.

package br.com.danielcosta.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
    //copia os valoes/propriedade de um objeto para outro objeto. Mescla as informações do result com o que não é null
    public static void copyNonNullProperties(Object source, Object Target){//static é usado para não precisar instanciar a classe
        BeanUtils.copyProperties(source, Target, getNullPropertyNames(source));
    }

    public static String[] getNullPropertyNames(Object source){
        // BeanWrapper e uma interface para acessar as propriedades de um objeto, e a BeanWrapperImpl e implementação da interface
        final BeanWrapper src = new BeanWrapperImpl(source);

        //gera arry com todas as propriedades do meu objeto.
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        //cria conjunto com as propriedades dos valores nulos. Inserindo as informações
        Set<String> emptyNames = new HashSet<>();

        //pega as propriedades do arry e verifica se ela é null
        for(PropertyDescriptor pd: pds){
            Object srcValue = src.getPropertyValue(pd.getName()); 

            //se Null passa para dentro do emptyNames.
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        //crian um arry com as informações do emptyNames
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);

    }
}
