/**
 * 
 */
package org.jl7.dsl

import org.jl7.hl7.*;

/**
 * @author henribenoit
 *
 */
public class HL7GroovyMessage{
	def HL7Message msg
	
	def HL7GroovyMessage(HL7Message msg) {
		this.msg = msg
	}
	
	def HL7GroovyMessage(String msg) {
		this.msg = HL7Parser.parseMessage(msg, true)
	}
	
	def HL7GroovyMessage(HL7Message msg, Closure args) {
		this(msg)
		args()
	}
	
	def methodMissing(String name, args) {
		def segments = new HL7GroovySegments(msg[name])
		if(args[0] instanceof Integer) {
			HL7GroovyMessage.metaClass."$name" = { Object[] varArgs ->
				def segment = segments[1];
				return segment[varArgs[0]];
	        }
			def segment = segments[1];
			return segment[args[0]];
		}
		println(args[0].getClass());
	}

   def propertyMissing(String type) {
		def items = type.split("\\_");
		if (items.size() > 0) {
			def segments = new HL7GroovySegments(msg[items[0]])
			if (items.size() > 1) {
				def segment = segments[1];
				int fieldIndex = Integer.parseInt(items[1]);
				if (items.size() > 2) {
					
				}
				else {
					return segment[fieldIndex];
				}
			}
			else {
				return segments;
			}
		}
		return null;
	}	
	
	def String toString() {
		return msg.toString()
	}
}
