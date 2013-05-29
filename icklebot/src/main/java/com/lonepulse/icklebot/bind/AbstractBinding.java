package com.lonepulse.icklebot.bind;

/*
 * #%L
 * IckleBot Library
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


import android.view.View;

/**
 * <p>This is an abstract implementation of a {@link Binding} which mandates the use 
 * of a parameterized constructor that supplies the arguments to the binding strategy.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class AbstractBinding<V extends View, E> implements Binding<V, E> {

	
	/**
	 * <p>The {@link View} to which the data is to be bound.
	 */
	protected V view;
	
	/**
	 * <p>The data which is to be bound to the {@link View}.
	 */
	protected E data;

	
	/**
	 * <p>Instantiates a new {@link AbstractBinding} with the given {@link View} and data. 
	 * 
	 * @param view
	 * 			the {@link View} to which the data is to be bound
	 * 
	 * @param data
	 * 			the data to be bound to the {@link View}
	 *
	 * @since 1.1.0
	 */
	public AbstractBinding(V view, E data) {
	
		this.view = view;
		this.data = data;
	}
	
	/**
	 * <p>Retrieves the {@link View} to which the data is to be bound. 
	 *
	 * @return the {@link View} to which the data is to be bound
	 * 
	 * @since 1.1.0
	 */
	public V getView() {
		
		return this.view;
	}
	
	/**
	 * <p>Retrieves the data which is to be bound. 
	 *
	 * @return the data which is to be bound
	 * 
	 * @since 1.1.0
	 */
	public E getData() {
		
		return this.data;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bind() throws BindException {
		
		try {
			
			onBind(data, view);
		}
		catch(Exception e) {
			
			throw new BindException(data, view);
		}
		
	}

	/**
	 * <p>Declares the strategy which will perform a <b>unidirectional binding</b> 
	 * from data to view.
	 * 
	 * @param data
	 * 			the data which is to be bound to the {@link View}.
	 * 			if binding data to the view failed
	 * 
	 * @param view
	 * 			the {@link View} to which the data is to be bound
	 * <br><br>
	 * @since 1.1.0
	 */
	public abstract void onBind(E data, V view);
}
