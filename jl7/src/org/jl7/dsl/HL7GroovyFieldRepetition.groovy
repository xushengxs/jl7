/**
 * 
 */
package org.jl7.dsl

import org.jl7.hl7.*;

/**
 * @author henribenoit
 *
 */
public class HL7GroovyFieldRepetition{
	def repetition;
	
	def HL7GroovyFieldRepetition(HL7FieldRepetition repetition) {
		this.repetition = repetition
	}
	
	def String toString() {
		return repetition.toString();
	}
	
	def getAt(int index) {
		return new HL7GroovyComponent(repetition[index])
	}

	def call(index) {
		return this[index];
	}
}
