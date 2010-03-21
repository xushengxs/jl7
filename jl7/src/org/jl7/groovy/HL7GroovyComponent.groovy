/**
 * 
 */
package org.jl7.groovy

import org.jl7.hl7.*;

/**
 * @author henribenoit
 *
 */
public class HL7GroovyComponent{
	def component;
	
	def HL7GroovyComponent(HL7Component component) {
		this.component = component
	}
	
	def String toString() {
		return component.toString();
	}
	
	def getAt(int index) {
		return new HL7GroovySubcomponent(component[index])
	}

	def call(index) {
		return this[index];
	}
}
