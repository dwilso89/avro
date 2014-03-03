package dewilson.projects.avro.serialization;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import dewilson.projects.avro.model.Record;
import dewilson.projects.avro.serialization.Serializer;

public class SerializeTest {
	private static Record record;

	@BeforeClass
	public static void setup() {
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
	public void serializeDeserializeFile() throws IOException {
		// Single
		File file = new File("target/tempTestSerializeFile.avro");
		Serializer serializer = new Serializer();
		serializer.serializeToFile(record, file);
		assertEquals(1, serializer.deserializeFromFile(file).size());
		file.delete();

		// List
		List<Record> records = new LinkedList<Record>();
		for (int i = 0; i < 100; i++) {
			records.add(Record.newBuilder(record).build());
		}

		serializer.serializeToFile(records, file);
		assertEquals(100, serializer.deserializeFromFile(file).size());
		file.delete();
	}

	@Test
	public void serializeDeserializeBytes() throws IOException {
		// Single
		Serializer serializer = new Serializer();
		byte[] bytes = serializer.serializeToBytes(record);
		assertEquals(1, serializer.deserializeFromBytes(bytes).size());
		bytes = null;

		// List
		List<Record> records = new LinkedList<Record>();
		for (int i = 0; i < 100; i++) {
			records.add(Record.newBuilder(record).build());
		}

		bytes = serializer.serializeToBytes(records);
		assertEquals(100, serializer.deserializeFromBytes(bytes).size());
	}

	@Test
	public void serializeDeserializeStream() throws IOException {
		// Single
		Serializer serializer = new Serializer();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		serializer.serializeToStream(record, out);
		InputStream in = new ByteArrayInputStream(out.toByteArray());
		assertEquals(1, serializer.deserializeFromStream(in).size());
		out = null;
		in = null;

		// List
		List<Record> records = new LinkedList<Record>();
		for (int i = 0; i < 100; i++) {
			records.add(Record.newBuilder(record).build());
		}

		out = new ByteArrayOutputStream();
		serializer.serializeToStream(records, out);
		in = new ByteArrayInputStream(out.toByteArray());
		assertEquals(100, serializer.deserializeFromStream(in).size());
	}
}
