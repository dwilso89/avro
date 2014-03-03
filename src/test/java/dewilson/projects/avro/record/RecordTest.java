package dewilson.projects.avro.record;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import dewilson.projects.avro.model.Record;
import dewilson.projects.avro.util.PrettyPrint;

public class RecordTest {

	@Test
	public void buildRecord() {
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

		Record record = Record.newBuilder().setData(data).setMetadata(metadata)
				.build();

		System.out.println(PrettyPrint.prettyPrintJson(record.toString()));
	}
}
