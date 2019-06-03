package org.leo.web.rest.convert;

/**
 * 布尔转换器
 * @author Jacob.Su
 * @mail: 450416583@qq.com
 */
public class BooleanConverter implements Converter<Boolean> {
    @Override
    public Boolean convert(Object source) {
        return Boolean.valueOf((String)source);
    }
}
