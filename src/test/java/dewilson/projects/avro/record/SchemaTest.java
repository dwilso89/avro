package dewilson.projects.avro.record;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.junit.Test;

public class SchemaTest {

	@Test
	public void validateSchema() throws IOException {
		new Schema.Parser().parse(new File("src/main/avro/schema.avro"));
	}

	@Test
	public void populaterecord() {

	}

}
