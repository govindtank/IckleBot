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
 * <p>Represents a simple mapping between one unit of data and a view and wraps 
 * the strategy which will be used to perform binding depending on the type of 
 * data and the view. This essentially serves as the <b>view-model</b>.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface Binding<V extends View, E> {

	/**
	 * <p>The strategy which will perform a <b>unidirectional binding</b> 
	 * from data to view. Be sure to post invocation to the UI thread. 
	 * 
	 * @throws BindException 
	 * 			if binding data to the view failed
	 * <br><br>
	 * @since 1.1.0
	 */
	void bind() throws BindException;
}
