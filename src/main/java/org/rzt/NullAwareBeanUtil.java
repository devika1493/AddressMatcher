package org.rzt;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtilsBean;


/**
 * 
 * Copying from one field to anther field only non null values
 * 
 * @author muthamizhan
 *
 */
public class NullAwareBeanUtil extends BeanUtilsBean {

	@Override
	public void copyProperty( Object dest, String name, Object value )
			throws IllegalAccessException, InvocationTargetException
	{
		if( value == null || value.equals("null") )
			return;
		super.copyProperty(dest, name, value);
	}
}