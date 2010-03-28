/**
 * 
 */
package org.jl7.dsl

import org.jl7.hl7.*;

/**
 * @author henribenoit
 *
 */
public class HL7GroovySegment{
	def segment;
	
	def HL7GroovySegment(HL7Segment segment) {
		this.segment = segment
	}
	
	def String toString() {
		return segment.toString();
	}
	
	def HL7GroovyField getAt(int index) {
		return new HL7GroovyField(segment[index])
	}

	def call(index) {
			return new HL7GroovyField(segment[index]);
	}
}
