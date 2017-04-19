package com.github.lwz0316.httpagent;

import com.github.lwz0316.httpagent.exection.ParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by liuwenzhu on 2015/12/14.
 */
public final class TypeUtil {

    private TypeUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 得到 泛型的类型
     *
     * <p>使用该方法的前提是在实例化对象时使用其的匿名内部类，这样才可以得到泛型的类型
     *
     * @see #getGenericType(Class, int)
     *
     * @param clazz 匿名内部类
     * @return
     * @throws Exception 若没有使用匿名内部类，则调用该方法时会抛出异常
     */
    public static Type getGenericType(Class<?> clazz) throws ParseException {
        return getGenericType(clazz, 0);
    }

    /**
     * 得到 泛型的类型
     *
     * <p>使用该方法的前提是在实例化对象时使用其的匿名内部类，这样才可以得到泛型的类型
     *
     * @param clazz 匿名内部类
     * @param index 第几个泛型, 下标从 0 开始
     * @return
     * @throws Exception 若没有使用匿名内部类，则调用该方法时会抛出异常
     */
    public static Type getGenericType(Class<?> clazz, int index) throws ParseException {
        Type mySuperClass = clazz.getGenericSuperclass();
        try {
            Type type = ((ParameterizedType) mySuperClass) .getActualTypeArguments()[index];
            return type;
        } catch (ClassCastException e) {
            throw new ParseException(
                    "please use AnonymousInner Class to get generic Type");
        }
    }

}
