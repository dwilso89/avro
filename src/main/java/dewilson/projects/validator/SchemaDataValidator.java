  package dewilson.projects.validator;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;

public class SchemaDataValidator implements DataValidator {

	private final Map<String, Schema> schemaMap = new ConcurrentHashMap<String, Schema>();
	private final Parser parser = new Schema.Parser();

	SchemaDataValidator() {

	}

	SchemaDataValidator(Iterable<Schema> schemas) {
		for (Schema schema : schemas) {
			schemaMap.put(schema.getName(), parser.parse(schema.toString()));
		}
	}

	SchemaDataValidator(Map<String, Schema> schemaMap) {
		for (Entry<String, Schema> schemaEntry : schemaMap.entrySet()) {
			schemaMap.put(schemaEntry.getKey(),
					parser.parse(schemaEntry.getValue().toString()));
		}
	}

	@Override
	public boolean isValidData(Map<CharSequence, List<ByteBuffer>> data) {
		Schema schema;
		for (Entry<CharSequence, List<ByteBuffer>> entry : data.entrySet()) {
			schema = schemaMap.get(entry.getKey().toString());

			if (schema != null) {

			} else {
				break;
			}
			return true;
		}
		return false;
	}
}
