package com.lonepulse.icklebot.injector;

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

import java.lang.annotation.Annotation;

import com.lonepulse.icklebot.activity.IckleActivity;

/**
 * <p>A {@link RuntimeException} which signals an unexpected failure 
 * in an {@link Injector}. These are usually unrecoverable and as such 
 * must be notified to the user in a polite manner.</p> 
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class DuplicateInjectionException extends InjectionException {


	private static final long serialVersionUID = 8618674483928650559L;
	

	/**
	 * <p>Logs additional information about the {@link IckleActivity} 
	 * whose injection failed and the {@link Annotation} which was used 
	 * multiple times.</p>
	 * 
	 * @param injectorContext
	 * 			the {@link Class} of the {@link IckleActivity} which 
	 * 			contains duplicate injections 
	 * <br><br>
	 * @param annotation
	 * 			the {@link Annotation} which cannot be used multiple 
	 * 			times on the same {@link IckleActivity} 
	 * <br><br>
	 * @since 1.0.0
	 */
	public DuplicateInjectionException(Class<?> injectorContext,
									   Class<? extends Annotation> annotation) {
		
		this("Activity " + injectorContext.getName() + " contains multiple injections of type " +
			  annotation.getName() + ". The annotation " + annotation.getSimpleName() + 
			  " may only be used once on a single context.");
	}
	
	/**
	 * See {@link InjectionException#InjectionException()} 
	 */
	public DuplicateInjectionException() {
	}

	/**
	 * See {@link InjectionException#InjectionException(String)} 
	 */
	public DuplicateInjectionException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * See {@link InjectionException#InjectionException(Throwable)} 
	 */
	public DuplicateInjectionException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * See {@link InjectionException#InjectionException(String, Throwable)} 
	 */
	public DuplicateInjectionException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
