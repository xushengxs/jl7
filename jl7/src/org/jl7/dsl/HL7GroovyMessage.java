/**
 *
 */
package org.jl7.dsl;

import java.lang.reflect.Method;

import groovy.lang.Closure;
import groovy.lang.ExpandoMetaClass;
import groovy.lang.MetaMethod;
import groovy.lang.MissingPropertyException;

import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;

/**
 * @author henribenoit
 *
 */
public class HL7GroovyMessage {
    private HL7Message msg;

    public HL7GroovyMessage(HL7Message msg) {
        this.msg = msg;
    }

    public HL7GroovyMessage(String msg) {
        this.msg = HL7Parser.parseMessage(msg, true);
    }

    public HL7GroovyMessage(HL7Message msg, Closure args) {
        this(msg);
        args.call();
    }

    public Object methodMissing(String name, Object args) {
        final Object[] varArgs = (Object[]) args;
        final String varName = name;
        final HL7GroovySegments segments = new HL7GroovySegments(msg.get(name));
        if(varArgs[0] instanceof Integer) {
            HL7GroovySegment segment = segments.getAt(1);
            return segment.getAt((Integer) varArgs[0]);
        }
        return null;
    }

   public Object propertyMissing(String type) {
        String[] items = type.split("\\_");
        if (items.length > 0) {
            HL7GroovySegments segments = new HL7GroovySegments(msg.get(items[0]));
            if (items.length > 1) {
                HL7GroovySegment segment = segments.getAt(1);
                int fieldIndex = Integer.parseInt(items[1]);
                if (items.length > 2) {

                }
                else {
                    return segment.getAt(fieldIndex);
                }
            }
            else {
                return segments;
            }
        }
        return null;
    }

   public void setProperty(String name, Object value) {
        if (value instanceof String) {
            msg.addSegment((String)value, msg.getDelimiters(), true);
        }
        else {
            throw new MissingPropertyException(name, (String)value, this.getClass());
        }
    }

   public HL7GroovyMessage leftShift(String segment) {
        msg.addSegment(segment, msg.getDelimiters(), true);
        return this;
    }

   public String toString() {
        return msg.toString();
    }

}
