package dewilson.projects.validator;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

public class FieldValidator implements DataValidator {

	private final List<String> fieldList = new CopyOnWriteArrayList<String>();

	public FieldValidator() {

	}

	public FieldValidator(Iterable<String> fields) {
		for (String field : fields) {
			addValidField(field);
		}
	}

	public void addValidField(String field) {
		fieldList.add(new String(field));
	}

	@Override
	public boolean isValidData(Map<CharSequence, List<ByteBuffer>> data) {
		for (Entry<CharSequence, List<ByteBuffer>> entry : data.entrySet()) {
			if (!fieldList.contains(entry.getKey())) {
				return false;
			}
		}
		return true;
	}
}
