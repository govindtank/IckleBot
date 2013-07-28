package com.lonepulse.icklebot.bind.expressive;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;


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
 * <p>Aggregates all available operators and delegates services calls to 
 * their individual instances.
 * 
 * @version 1.1.3
 * <br><br>
 * @since 1.2.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum Op implements Operator {
	
	
	/**
	 * <p>Initiates the evaluation of an expression.</p>
	 * 
	 * <b>Usage:</b> <pre>${&lt;your-expression-here&gt;}</pre>
	 * 
	 * @since 1.2.1
	 */
	EVALUATE(new Evaluate()),
	
	/**
	 * <p>Performs a projection from a model attribute.</p>
	 * 
	 * <b>Usage:</b> <pre>${property1.property2.propertyN}</pre>
	 * 
	 * @since 1.2.1
	 */
	PROJECT_PROPERTY(new ProjectProperty()),
	
	/**
	 * <p>Performs the projection of an element in a {@link Collection} or an 
	 * array. If used on a {@link Map} it exposes an {@link Entry}.</p>
	 * 
	 * <b>Usage:</b> <pre>${arrayOrCollection[4]}</pre>
	 * 
	 * @since 1.2.1
	 */
	PROJECT_ELEMENT(new ProjectElement()),
	
	/**
	 * <p>Performs the projection of a value associated with a particular key 
	 * in {@link Map}s or {@link Properties}.</p>
	 * 
	 * <b>Usage:</b> <pre>${property1.property2.propertyN}</pre>
	 * 
	 * @since 1.2.1
	 */
	PROJECT_VALUE(new ProjectValue()),
	
	/**
	 * <p>Simulates an <b>Elvis Operator</b>.</p>
	 * 
	 * <b>Usage:</b> <pre>${property?:n/a}</pre>, <pre>${property?:'n/a'}</pre>, 
	 * <pre>${property?:\'n/a\'}</pre> or <pre>${property?:\"n/a\"}</pre>
	 * 
	 * @since 1.2.1
	 */
	ELVIS(new Elvis()),
	
	/**
	 * <p>Simulates a <b>Ternary Operator</b>.</p>
	 * 
	 * <b>Usage:</b> <pre>${property?textIfTrue:textIfFalse}</pre>, <pre>${property?'textIfTrue':'textIfFalse'}</pre>, 
	 * <pre>${property?\'textIfTrue\':\'textIfFalse\'}</pre> or <pre>${property?\"textIfTrue\":\"textIfFalse\"}</pre>
	 * 
	 * @since 1.2.1
	 */
	TERNARY(new Ternary());

	
	/**
	 * <p>The operator which this instance of {@link Op}s 
	 * delegates its calls to.
	 */
	private Operator operator;
	

	/**
	 * <p>Adds the given operator to this aggregation.
	 * 
	 * @param operator
	 * 			the {@link Operator} to which service calls are delegated
	 *
	 * @since 1.2.1
	 */
	private Op(Operator operator) {
		
		this.operator = operator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Symbol getSymbol() {
		
		return operator.getSymbol();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object evaluate(Field attribute, Object target, Object... args) throws OperationFailedException {
		
		return operator.evaluate(attribute, target, args);
	}

	/**
	 * <p>Resolves an {@link Operator} for the given {@link Symbol#getHead()}.
	 * 
	 * @param head
	 * 			the {@link Symbol#getHead()} for the {@link Operator} 
	 * 
	 * @return the resolved {@link Op}
	 * 
	 * @throws OperatorResolutionFailedException
	 * 			if the no operator was found with the given symbol-head
	 * 
	 * @since 1.2.1
	 */
	public static final Op resolve(String head) throws OperatorResolutionFailedException {
		
		Op[] values = values();
		
		for (Op op : values) {
			
			if(op.getSymbol().getHead().equals(head)) return op;
		}
		
		throw new OperatorResolutionFailedException();
	}
}
