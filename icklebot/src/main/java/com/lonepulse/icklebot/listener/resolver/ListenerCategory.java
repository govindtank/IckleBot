package com.lonepulse.icklebot.listener.resolver;

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

import android.view.View.OnClickListener;

/**
 * <p>Identifies the <i>category</i> to which a particular listener linking 
 * operation falls.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum ListenerCategory {

	/**
	 * <p>This <i>category</i> is responsible for identifying the 
	 * linking of {@link OnClickListener}s.
	 * 
	 * @since 1.1.0 
	 */
	CLICK;
}
