/**
 * 
 */
package org.jl7.groovy

import org.jl7.hl7.*;

/**
 * @author henribenoit
 *
 */
public class HL7{

	def static message(msg) {
		return new HL7GroovyMessage(msg)
	}

	def static message(msg, args) {
		def groovyMessage = new HL7GroovyMessage(msg)
		args();
	}
}
