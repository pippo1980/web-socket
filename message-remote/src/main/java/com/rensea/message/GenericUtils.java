/**
 *
 */
package com.rensea.message;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author pippo
 */
@SuppressWarnings("unchecked")
public final class GenericUtils {

	private static final Log log = LogFactory.getLog(GenericUtils.class);

	private GenericUtils() {
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
	 *
	 * @param clazz The class to introspect
	 * @return the first generic declaration, or <code>Object.class</code> if cannot be determined
	 */
	public static Class getSuperClassGenricType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
	 *
	 * @param clazz clazz The class to introspect
	 * @param index the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or <code>Object.class</code> if cannot be determined
	 */
	public static Class getSuperClassGenricType(Class clazz, int index) {
		Type genType = clazz.getGenericSuperclass();

		if (genType == Object.class) {
			log.warn(clazz.getSimpleName() + "'s supperclass is Object.class not ParameterizedType");
			/* 父类是object的情况下,尝试查找实现的接口 */
			Type[] types = clazz.getGenericInterfaces();
			if (types == null || types.length == 0) {
				return Object.class;
			} else

				genType = types[0];
		}

		if (genType instanceof Class) {
			log.warn(clazz.getSimpleName() + "'s superclass is " + genType
					+ " not ParameterizedType, find " + genType + "'s supperclass generic type");
			return getSuperClassGenricType((Class) genType, index);
		}

		if (!(genType instanceof ParameterizedType)) {
			log.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		} else {
			if (log.isDebugEnabled())
				log.debug(clazz.getSimpleName() + "'s superclass is:" + genType);
			return getSuperClassGenricType((ParameterizedType) genType, index);
		}
	}

	public static Class getSuperClassGenricType(ParameterizedType clazz, int index) {
		Type[] params = clazz.getActualTypeArguments();
		if (index >= params.length || index < 0) {
			log.warn("Index: " + index + ", Size of " + clazz + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			log.warn(clazz + " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return (Class) params[index];
	}

}
