/**
 * 
 */
package org.jl7.dsl

import org.jl7.hl7.*;
import org.jl7.hl7.HL7Subcomponent
/**
 * @author henribenoit
 *
 */
public class HL7GroovySubcomponent{
	def subcomponent;
	
	def HL7GroovySubcomponent(HL7Subcomponent subcomponent) {
		this.subcomponent = subcomponent
	}
	
	def String toString() {
		return subcomponent.toString();
	}
}
