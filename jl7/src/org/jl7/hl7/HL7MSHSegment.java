package org.jl7.hl7;

// TODO: Auto-generated Javadoc
/**
 * The Class HL7MSHSegment.
 */
public class HL7MSHSegment extends HL7Segment {
    
    /**
     * Gets the accept acknowledgement type.
     *
     * @return the accept acknowledgement type
     */
    public String getAcceptAcknowledgementType() {
        return get(14).toString();
    }

    /**
     * Gets the application acknowledgement type.
     *
     * @return the application acknowledgement type
     */
    public String getApplicationAcknowledgementType() {
        return get(15).toString();
    }

    /**
     * Gets the character set.
     *
     * @return the character set
     */
    public String getCharacterSet() {
        return get(17).toString();
    }

    /**
     * Gets the continuation pointer.
     *
     * @return the continuation pointer
     */
    public String getContinuationPointer() {
        return get(13).toString();
    }

    /**
     * Gets the country code.
     *
     * @return the country code
     */
    public String getCountryCode() {
        return get(16).toString();
    }

    /**
     * Gets the event type.
     *
     * @return the event type
     */
    public String getEventType() {
        return get(8).get(0).get(1).toString();
    }

    /**
     * Gets the message control id.
     *
     * @return the message control id
     */
    public String getMessageControlId() {
        return get(9).toString();
    }

    /**
     * Gets the message date time.
     *
     * @return the message date time
     */
    public String getMessageDateTime() {
        return get(6).toString();
    }

    /**
     * Gets the message event type.
     *
     * @return the message event type
     */
    public String getMessageEventType() {
        return get(8).toString();
    }

    /**
     * Gets the message type.
     *
     * @return the message type
     */
    public String getMessageType() {
        return get(8).get(0).get(0).toString();
    }

    /**
     * Gets the principal language.
     *
     * @return the principal language
     */
    public String getPrincipalLanguage() {
        return get(18).toString();
    }

    /**
     * Gets the processing id.
     *
     * @return the processing id
     */
    public String getProcessingId() {
        return get(10).toString();
    }

    /**
     * Gets the receiving application.
     *
     * @return the receiving application
     */
    public String getReceivingApplication() {
        return get(4).toString();
    }

    /**
     * Gets the receiving facility.
     *
     * @return the receiving facility
     */
    public String getReceivingFacility() {
        return get(5).toString();
    }

    /**
     * Gets the sending application.
     *
     * @return the sending application
     */
    public String getSendingApplication() {
        return get(2).toString();
    }

    /**
     * Gets the sending facility.
     *
     * @return the sending facility
     */
    public String getSendingFacility() {
        return get(3).toString();
    }

    /**
     * Gets the sequence number.
     *
     * @return the sequence number
     */
    public int getSequenceNumber() {
        return Integer.parseInt(get(12).toString());
    }

    /**
     * Gets the version id.
     *
     * @return the version id
     */
    public String getVersionId() {
        return get(11).toString();
    }

    /* (non-Javadoc)
     * @see org.jl7.hl7.HL7Segment#setFields(java.lang.String[], java.lang.String, boolean)
     */
    @Override
    public void setFields(String[] fields, String delimiters, boolean escapesInSubcomponents) {
        this.fields.clear();
        boolean first = true;
        boolean second = false;
        for (String field : fields) {
            if (first) {
                this.fields.add(HL7Parser.parseField(field, delimiters, escapesInSubcomponents));
                first = false;
                second = true;
            }
            else if (second) {
                // Do not interpret delimiters for the first MSH segment (MSH-2
                // in the standard)
                this.fields.add(HL7Parser.parseMSHFirstField(field, delimiters));
                second = false;
            }
            else {
                this.fields.add(HL7Parser.parseField(field, delimiters, escapesInSubcomponents));
            }
        }
    }
}
