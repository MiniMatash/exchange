package com.minimatash.exchangetest.util;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

public final class DtoConverter {

    private static final BeanUtilsBean beanUtilsBean = new NullAwareBeanUtilsBean();

    private DtoConverter() {}

    public static <F, T> T convert(T to, F from) throws InvocationTargetException, IllegalAccessException {
        beanUtilsBean.copyProperties(to, from);
        return to;
    }
}
