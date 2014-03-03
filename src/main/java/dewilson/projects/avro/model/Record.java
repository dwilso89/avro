package dewilson.projects.avro.model;

/** Generic Data Holder */
@org.apache.avro.specific.AvroGenerated
public class Record extends org.apache.avro.specific.SpecificRecordBase
		implements org.apache.avro.specific.SpecificRecord {
	public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser()
			.parse("{\"type\":\"record\",\"name\":\"Record\",\"namespace\": \"dewilson.projects.avro.record\",\"doc\":\"Generic Data Holder\",\"fields\":[{\"name\":\"Data\",\"type\":{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":\"bytes\"}}},{\"name\":\"Metadata\",\"type\":{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":\"string\"}}}]}");

	public static org.apache.avro.Schema getClassSchema() {
		return SCHEMA$;
	}

	private java.util.Map<java.lang.CharSequence, java.util.List<java.nio.ByteBuffer>> Data;
	private java.util.Map<java.lang.CharSequence, java.util.List<java.lang.CharSequence>> Metadata;

	/**
	 * Default constructor. Note that this does not initialize fields to their
	 * default values from the schema. If that is desired then one should use
	 * <code>newBuilder()</code>.
	 */
	public Record() {
	}

	/**
	 * All-args constructor.
	 */
	public Record(
			java.util.Map<java.lang.CharSequence, java.util.List<java.nio.ByteBuffer>> Data,
			java.util.Map<java.lang.CharSequence, java.util.List<java.lang.CharSequence>> Metadata) {
		this.Data = Data;
		this.Metadata = Metadata;
	}

	public org.apache.avro.Schema getSchema() {
		return SCHEMA$;
	}

	// Used by DatumWriter. Applications should not call.
	public java.lang.Object get(int field$) {
		switch (field$) {
		case 0:
			return Data;
		case 1:
			return Metadata;
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}

	// Used by DatumReader. Applications should not call.
	@SuppressWarnings(value = "unchecked")
	public void put(int field$, java.lang.Object value$) {
		switch (field$) {
		case 0:
			Data = (java.util.Map<java.lang.CharSequence, java.util.List<java.nio.ByteBuffer>>) value$;
			break;
		case 1:
			Metadata = (java.util.Map<java.lang.CharSequence, java.util.List<java.lang.CharSequence>>) value$;
			break;
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}

	/**
	 * Gets the value of the 'Data' field.
	 */
	public java.util.Map<java.lang.CharSequence, java.util.List<java.nio.ByteBuffer>> getData() {
		return Data;
	}

	/**
	 * Sets the value of the 'Data' field.
	 * 
	 * @param value
	 *            the value to set.
	 */
	public void setData(
			java.util.Map<java.lang.CharSequence, java.util.List<java.nio.ByteBuffer>> value) {
		this.Data = value;
	}

	/**
	 * Gets the value of the 'Metadata' field.
	 */
	public java.util.Map<java.lang.CharSequence, java.util.List<java.lang.CharSequence>> getMetadata() {
		return Metadata;
	}

	/**
	 * Sets the value of the 'Metadata' field.
	 * 
	 * @param value
	 *            the value to set.
	 */
	public void setMetadata(
			java.util.Map<java.lang.CharSequence, java.util.List<java.lang.CharSequence>> value) {
		this.Metadata = value;
	}

	/** Creates a new record RecordBuilder */
	public static Record.Builder newBuilder() {
		return new Record.Builder();
	}

	/** Creates a new record RecordBuilder by copying an existing Builder */
	public static Record.Builder newBuilder(Record.Builder other) {
		return new Record.Builder(other);
	}

	/**
	 * Creates a new record RecordBuilder by copying an existing record instance
	 */
	public static Record.Builder newBuilder(Record other) {
		return new Record.Builder(other);
	}

	/**
	 * RecordBuilder for record instances.
	 */
	public static class Builder extends
			org.apache.avro.specific.SpecificRecordBuilderBase<Record>
			implements org.apache.avro.data.RecordBuilder<Record> {

		private java.util.Map<java.lang.CharSequence, java.util.List<java.nio.ByteBuffer>> Data;
		private java.util.Map<java.lang.CharSequence, java.util.List<java.lang.CharSequence>> Metadata;

		/** Creates a new Builder */
		private Builder() {
			super(Record.SCHEMA$);
		}

		/** Creates a Builder by copying an existing Builder */
		private Builder(Record.Builder other) {
			super(other);
			if (isValidValue(fields()[0], other.Data)) {
				this.Data = data().deepCopy(fields()[0].schema(), other.Data);
				fieldSetFlags()[0] = true;
			}
			if (isValidValue(fields()[1], other.Metadata)) {
				this.Metadata = data().deepCopy(fields()[1].schema(),
						other.Metadata);
				fieldSetFlags()[1] = true;
			}
		}

		/** Creates a Builder by copying an existing record instance */
		private Builder(Record other) {
			super(Record.SCHEMA$);
			if (isValidValue(fields()[0], other.Data)) {
				this.Data = data().deepCopy(fields()[0].schema(), other.Data);
				fieldSetFlags()[0] = true;
			}
			if (isValidValue(fields()[1], other.Metadata)) {
				this.Metadata = data().deepCopy(fields()[1].schema(),
						other.Metadata);
				fieldSetFlags()[1] = true;
			}
		}

		/** Gets the value of the 'Data' field */
		public java.util.Map<java.lang.CharSequence, java.util.List<java.nio.ByteBuffer>> getData() {
			return Data;
		}

		/** Sets the value of the 'Data' field */
		public Record.Builder setData(
				java.util.Map<java.lang.CharSequence, java.util.List<java.nio.ByteBuffer>> value) {
			validate(fields()[0], value);
			this.Data = value;
			fieldSetFlags()[0] = true;
			return this;
		}

		/** Checks whether the 'Data' field has been set */
		public boolean hasData() {
			return fieldSetFlags()[0];
		}

		/** Clears the value of the 'Data' field */
		public Record.Builder clearData() {
			Data = null;
			fieldSetFlags()[0] = false;
			return this;
		}

		/** Gets the value of the 'Metadata' field */
		public java.util.Map<java.lang.CharSequence, java.util.List<java.lang.CharSequence>> getMetadata() {
			return Metadata;
		}

		/** Sets the value of the 'Metadata' field */
		public Record.Builder setMetadata(
				java.util.Map<java.lang.CharSequence, java.util.List<java.lang.CharSequence>> value) {
			validate(fields()[1], value);
			this.Metadata = value;
			fieldSetFlags()[1] = true;
			return this;
		}

		/** Checks whether the 'Metadata' field has been set */
		public boolean hasMetadata() {
			return fieldSetFlags()[1];
		}

		/** Clears the value of the 'Metadata' field */
		public Record.Builder clearMetadata() {
			Metadata = null;
			fieldSetFlags()[1] = false;
			return this;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Record build() {
			try {
				Record record = new Record();
				record.Data = fieldSetFlags()[0] ? this.Data
						: (java.util.Map<java.lang.CharSequence, java.util.List<java.nio.ByteBuffer>>) defaultValue(fields()[0]);
				record.Metadata = fieldSetFlags()[1] ? this.Metadata
						: (java.util.Map<java.lang.CharSequence, java.util.List<java.lang.CharSequence>>) defaultValue(fields()[1]);
				return record;
			} catch (Exception e) {
				throw new org.apache.avro.AvroRuntimeException(e);
			}
		}
	}
}
