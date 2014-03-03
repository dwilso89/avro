package dewilson.projects.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import dewilson.projects.avro.model.Record;
import dewilson.projects.validator.DataValidator;
import dewilson.projects.validator.FieldValidator;

public class FieldValidatorTest {

	Record record;

	@Before
	public void setup() {
		Map<CharSequence, List<ByteBuffer>> data = new HashMap<CharSequence, List<ByteBuffer>>();
		List<ByteBuffer> ips = new LinkedList<ByteBuffer>();
		ips.add(ByteBuffer.wrap("10.0.0.1".getBytes()));
		ips.add(ByteBuffer.wrap("127.0.0.1".getBytes()));
		data.put("ip", ips);

		List<ByteBuffer> ports = new LinkedList<ByteBuffer>();
		ByteBuffer port = (ByteBuffer.allocate(4).putInt(new Integer(80)));
		port.position(0);
		ports.add(port);
		port = (ByteBuffer.allocate(4).putInt(443));
		port.position(0);
		ports.add(port);
		data.put("port", ports);

		List<ByteBuffer> macs = new LinkedList<ByteBuffer>();
		macs.add(ByteBuffer.wrap("01:23:45:67:89:ab".getBytes()));
		macs.add(ByteBuffer.wrap("98:76:54:32:10:zx".getBytes()));
		data.put("mac", macs);

		Map<CharSequence, List<CharSequence>> metadata = new HashMap<CharSequence, List<CharSequence>>();
		List<CharSequence> timestamps = new LinkedList<CharSequence>();
		timestamps.add((new Date()).toString());
		timestamps.add((new Date()).toString());
		metadata.put("timestamps", timestamps);

		List<CharSequence> uuids = new LinkedList<CharSequence>();
		uuids.add(UUID.randomUUID().toString());
		uuids.add(UUID.randomUUID().toString());
		metadata.put("uuids", uuids);

		record = Record.newBuilder().setData(data).setMetadata(metadata)
				.build();
	}

	@Test
	public void validData() {
		DataValidator fieldValidator;
		List<String> fields = new LinkedList<String>();
		fields.add("port");
		fields.add("ip");
		fields.add("mac");
		fieldValidator = new FieldValidator(fields);
		assertTrue(fieldValidator.isValidData(record.getData()));
	}

	@Test
	public void invalidData() {
		DataValidator fieldValidator = new FieldValidator();
		assertFalse(fieldValidator.isValidData(record.getData()));
	}
}
