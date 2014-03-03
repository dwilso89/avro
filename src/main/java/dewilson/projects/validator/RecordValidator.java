package dewilson.projects.validator;

import dewilson.projects.avro.model.Record;


public interface RecordValidator {

	boolean isValidRecord(Record record);

	MetaDataValidator getMetaDataValidator();

	DataValidator getDataValidator();
}
