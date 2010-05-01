package org.jl7.dsl

import org.jl7.hl7.*;

/**
 * @author henribenoit
 *
 */
public class HL7GroovyField{
	def field;

	def HL7GroovyField(HL7Field field) {
		this.field = field
	}

	def String toString() {
		return field.toString();
	}

	def getAt(int index) {
		return new HL7GroovyFieldRepetition(field[index])
	}

	def call(index) {
		def repetition = new HL7GroovyFieldRepetition(field[1])
		return repetition[index];
	}
}
