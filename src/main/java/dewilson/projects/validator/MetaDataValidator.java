package dewilson.projects.validator;

import java.util.List;
import java.util.Map;

public interface MetaDataValidator {

	boolean isValidMetaData(Map<CharSequence, List<CharSequence>> metadata);

}
