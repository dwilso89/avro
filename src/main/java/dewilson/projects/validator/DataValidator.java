package dewilson.projects.validator;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public interface DataValidator {

	boolean isValidData(Map<CharSequence, List<ByteBuffer>> data);

}
