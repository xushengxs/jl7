/**
 * 
 */
package org.jl7.groovy

import org.jl7.hl7.*;

/**
 * @author henribenoit
 *
 */
public class HL7GroovySegments{
	def List<HL7Segment> segments;
	
	public HL7GroovySegments(List<HL7Segment> segments) {
		this.segments=segments;
	}
	
	def String toString() {
		return segments.toString()
	}
	
	def HL7GroovySegment getAt(int index) {
		return new HL7GroovySegment(segments.get(index - 1))
	}
	
}
