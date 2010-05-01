package org.jl7.dsl

import org.jl7.hl7.*;

/**
 * @author henribenoit
 *
 */
public class TestGroovy{

	static void main(def args){
		def MESSAGE = "MSH|^~\\&||ABCHS||AUSDHSV|20070103112951||ADT^A08^ADT_A01|12334456778893|P|2.5|||NE|NE|AU|ASCII\rEVN|A08|20060705000000\rPID|1||0000112234^^1&2&3^100^A||XXXXXXXXXX^^^^^^S~YYYY^^A&B&C^^^^F||10131113|1||4|^^RICHMOND^^3121||||1201||||||||1100|||||||||AAA\rPV1|1|O|^^^^^1|||||||2|||||1||||654345509^^^100^A|1|||||||||||||||||||||||||200607050000||||||V\rPV2|||||||1||||||||||||||||^^^^^^^^^103\rROL|1|AD|SAHCP|XXXXXXXXXX^^^^^^S|||||6|1\rPR1|1||1||20060705|1\rGT1|1||||||||||||||||||||NOT APPLICABLE"

		def javaMsg = HL7Parser.parseMessage(MESSAGE, true)
		javaMsg['PID'][0][2] = 'blabla'
		println "1:\n"+javaMsg
		
		def groovyMsg = HL7.message(MESSAGE)
		println "2:\n"+groovyMsg
		println "3:\n"+groovyMsg.PID
		println "4:\n"+groovyMsg.PID(5)
		println "5:\n"+groovyMsg.PID[1](5)
		println "6:\n"+groovyMsg.PID[1](5)(1)
		println "7:\n"+groovyMsg.PID(5)[2]
		println "8:\n"+groovyMsg.PID(5)[2](3)
		println "9:\n"+groovyMsg.PID(5)[2](3)(2)
	}
}
