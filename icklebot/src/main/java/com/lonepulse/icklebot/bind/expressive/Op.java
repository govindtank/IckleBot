package com.lonepulse.icklebot.bind.expressive;

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
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum Op implements Operator {
	
	
	/**
	 * <p>Performs a projection from a model attribute.</p>
	 * 
	 * <b>Usage:</b> <pre>${tag.attr1.attr2}</pre>
	 */
	PROJECT(new Projection());

	
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
	 * @since 1.1.0
	 */
	private Op(Operator operator) {
		
		this.operator = operator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Symbol symbol() {
		
		return operator.symbol();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object evaluate(Object target, Object... args) throws OperationFailedException {
		
		return operator.evaluate(target, args);
	}
}
