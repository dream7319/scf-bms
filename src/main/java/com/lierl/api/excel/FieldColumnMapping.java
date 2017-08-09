package com.lierl.api.excel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lierl
 * @create 2017-08-08 14:01
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldColumnMapping {
	/**
	 * 放在第几列
	 * @return
	 */
	int column();

	/**
	 * 标题
	 * @return
	 */
	String title();
}
