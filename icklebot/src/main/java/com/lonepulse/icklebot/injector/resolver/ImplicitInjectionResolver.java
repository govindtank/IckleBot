package com.lonepulse.icklebot.injector.resolver;

/*
 * #%L
 * IckleBot
 * %%
 * Copyright (C) 2013 Lonepulse
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.telephony.TelephonyManager;
import android.view.View;

import com.lonepulse.icklebot.annotation.inject.ApplicationContract;
import com.lonepulse.icklebot.annotation.inject.IgnoreInjection;
import com.lonepulse.icklebot.annotation.inject.InjectAll;
import com.lonepulse.icklebot.annotation.inject.Pojo;
import com.lonepulse.icklebot.event.resolver.EventCategory;
import com.lonepulse.icklebot.event.resolver.EventResolver;

/**
 * <p>An implementation of {@link EventResolver} which caters to 
 * <b>Implicit Injections</b> activated via {@link InjectAll}.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ImplicitInjectionResolver implements InjectionResolver {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public InjectionCategory resolve(Field field) {
		
		if(field.isAnnotationPresent(IgnoreInjection.class))
			return InjectionCategory.NONE;
		
		else if(isCategoryApplication(field))
			return InjectionCategory.APPLICATION;
		
		else if(isCategoryResourceView(field))
			return InjectionCategory.RESOURCE_VIEW;
		
		else if(isCategoryResourceInteger(field))
			return InjectionCategory.RESOURCE_INTEGER;
		
		else if(isCategoryResourceString(field))
			return InjectionCategory.RESOURCE_STRING;
		
		else if(isCategoryResourceDrawable(field))
			return InjectionCategory.RESOURCE_DRAWABLE;
		
		else if(isCategoryResourceDimension(field))
			return InjectionCategory.RESOURCE_DIMENSION;
		
		else if(isCategoryPojo(field))
			return InjectionCategory.POJO;
		
		else if(isCategoryService(field))
			return InjectionCategory.SERVICE;
		
		return InjectionCategory.NONE;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link EventCategory#APPLICATION}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link EventCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link EventCategory#APPLICATION}, 
	 * 			else {@code false}
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategoryApplication(Field field) {
	
		return field.getType().isAnnotationPresent(ApplicationContract.class);
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link EventCategory#RESOURCE_VIEW}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link EventCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link EventCategory#RESOURCE_VIEW}, 
	 * 			else {@code false}
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategoryResourceView(Field field) {
		
		return (View.class.isAssignableFrom(field.getType()))? true :false; 
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link EventCategory#RESOURCE_INTEGER}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link EventCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link EventCategory#RESOURCE_INTEGER}, 
	 * 			else {@code false}
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategoryResourceInteger(Field field) {
		
		return (Integer.class.isAssignableFrom(field.getType())
				|| int.class.isAssignableFrom(field.getType()))? true : false;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link EventCategory#RESOURCE_STRING}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link EventCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link EventCategory#RESOURCE_STRING}, 
	 * 			else {@code false}
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategoryResourceString(Field field) {
		
		return (String.class.isAssignableFrom(field.getType()))? true :false;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link EventCategory#RESOURCE_DRAWABLE}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link EventCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link EventCategory#RESOURCE_DRAWABLE}, 
	 * 			else {@code false}
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategoryResourceDrawable(Field field) {
		
		return (Drawable.class.isAssignableFrom(field.getType()))? true :false;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#RESOURCE_DIMENSION}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#RESOURCE_DRAWABLE}, 
	 * 			else {@code false}
	 * <br><br>
	 * @since 1.1.1
	 */
	private boolean isCategoryResourceDimension(Field field) {
		
		return (Float.class.isAssignableFrom(field.getType())
				|| float.class.isAssignableFrom(field.getType()))? true : false;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link EventCategory#POJO}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link EventCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link EventCategory#POJO}, 
	 * 			else {@code false}
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategoryPojo(Field field) {
		
		return field.getType().isAnnotationPresent(Pojo.class);
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link EventCategory#SERVICE}.</p>
	 * 
	 * <p>The {@link Field}'s <i>declared name</i> must match the 
	 * respective constant in {@link Context} (case insensitive). 
	 * For example, if an instance of {@link TelephonyManager} is 
	 * to be injected, the field declaration should be:</p>
	 * 
	 * <br><br>&nbsp;&nbsp;&nbsp;
	 * 
	 * {@code TelephonyManager TELEPHONY_SERVICE;}
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link EventCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link EventCategory#SERVICE}, 
	 * 			else {@code false}
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategoryService(Field field) {
		
		if(!(field.getName().endsWith("_service") 
			|| field.getName().endsWith("_SERVICE"))) {
			
			return false;
		}
		
		Field[] declaredFields = Context.class.getDeclaredFields();
		
		for (Field declaredField : declaredFields) {
			
			if(String.class.isAssignableFrom(declaredField.getType())
				&& declaredField.getName().equalsIgnoreCase(field.getName())) {
				
				return true;
			}
		}
		
		return false;
	}
}
