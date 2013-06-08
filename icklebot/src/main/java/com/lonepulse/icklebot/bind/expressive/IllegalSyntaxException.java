package com.lonepulse.icklebot.bind.expressive;

import com.lonepulse.icklebot.IckleBotRuntimeException;

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


/**
 * <p>An {@link IckleBotRuntimeException} which signals a failure to parse 
 * a given expression string into an {@link Expression} tree. 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class IllegalSyntaxException extends IckleBotRuntimeException {


	private static final long serialVersionUID = -8803756229222956378L;
	

	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException()}
	 */
	public IllegalSyntaxException() {}

	
	/**
	 * See {@link RuntimIckleBotRuntimeExceptioneException#IckleBotRuntimeException(String)} 
	 */
	public IllegalSyntaxException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException(Throwable)} 
	 */
	public IllegalSyntaxException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException(String, Throwable)} 
	 */
	public IllegalSyntaxException(String detailMessage, Throwable throwable) {
		
		super(detailMessage, throwable);
	}
}
