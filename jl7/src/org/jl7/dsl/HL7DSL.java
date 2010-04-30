/**
 * 
 */
package org.jl7.dsl;

import groovy.lang.Binding;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.codehaus.groovy.control.CompilationFailedException;
import org.jl7.hl7.HL7Message;

/**
 * @author henribenoit
 * 
 */
public class HL7DSL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Logger logger = Logger.getLogger(HL7DSL.class.getCanonicalName());

			Binding binding = new Binding();
			binding.setVariable("message", new HL7GroovyMessage(
					new HL7Message()));
			GroovyShell shell = new GroovyShell(binding);

			File dsl = new File(args[0]);

			logger.info("Loading " + dsl.getAbsolutePath());
			GroovyCodeSource code = new GroovyCodeSource(dsl);
			StringBuilder builder = new StringBuilder();
			builder.append("import static org.jl7.hl7.HL7Parser.*;\n");
			builder.append("import org.jl7.dsl.*;\n");
			builder.append(code.getScriptText());
			Script dslScript = shell.parse(dsl);
			logger.info("Running " + dsl.getAbsolutePath());
			dslScript.run();
		} catch (CompilationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
