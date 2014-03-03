package dewilson.projects.avro.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.FileReader;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import dewilson.projects.avro.model.Record;

public class Serializer {

	public Serializer() {

	}

	public byte[] serializeToBytes(Record record) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DatumWriter<Record> recordDatumWriter = new SpecificDatumWriter<Record>(
				Record.class);
		BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		recordDatumWriter.write(record, encoder);
		encoder.flush();
		out.close();
		return out.toByteArray();
	}

	public byte[] serializeToBytes(List<Record> records) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DatumWriter<Record> recordDatumWriter = new SpecificDatumWriter<Record>(
				Record.class);
		BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		for (Record record : records) {
			recordDatumWriter.write(record, encoder);
		}
		encoder.flush();
		out.close();
		return out.toByteArray();
	}

	public void serializeToStream(Record record, OutputStream out)
			throws IOException {
		DatumWriter<Record> recordDatumWriter = new SpecificDatumWriter<Record>(
				Record.class);
		BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		recordDatumWriter.write(record, encoder);
		out.flush();
		encoder.flush();
	}

	public void serializeToStream(List<Record> records, OutputStream out)
			throws IOException {
		DatumWriter<Record> recordDatumWriter = new SpecificDatumWriter<Record>(
				Record.class);
		BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		for (Record record : records) {
			recordDatumWriter.write(record, encoder);
		}
		encoder.flush();
	}

	public void serializeToFile(Record record, File file) throws IOException {
		DatumWriter<Record> recordDatumWriter = new SpecificDatumWriter<Record>(
				Record.class);
		DataFileWriter<Record> dataFileWriter = new DataFileWriter<Record>(
				recordDatumWriter);
		dataFileWriter.create(Record.SCHEMA$, file);
		dataFileWriter.append(record);
		dataFileWriter.close();
	}

	public void serializeToFile(List<Record> records, File file)
			throws IOException {
		DatumWriter<Record> recordDatumWriter = new SpecificDatumWriter<Record>(
				Record.class);
		DataFileWriter<Record> dataFileWriter = new DataFileWriter<Record>(
				recordDatumWriter);
		dataFileWriter.create(Record.SCHEMA$, file);
		for (Record record : records) {
			dataFileWriter.append(record);
		}
		dataFileWriter.close();
	}

	public List<Record> deserializeFromBytes(byte[] bytes) throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		return deserializeFromStream(in);
	}

	public List<Record> deserializeFromStream(InputStream in)
			throws IOException {
		DatumReader<Record> recordDatumReader = new SpecificDatumReader<Record>(
				Record.class);
		BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(in, null);
		List<Record> records = new LinkedList<Record>();
		Record record_reuse = new Record();
		while (!decoder.isEnd()) {
			if (recordDatumReader.read(record_reuse, decoder) != null)
				records.add(Record.newBuilder(record_reuse).build());
		}
		return records;
	}

	public List<Record> deserializeFromFile(File file) throws IOException {
		DatumReader<Record> recordDatumReader = new SpecificDatumReader<Record>(
				Record.class);
		FileReader<Record> dataFileReader = DataFileReader.openReader(file,
				recordDatumReader);
		List<Record> records = new LinkedList<Record>();
		while (dataFileReader.hasNext()) {
			records.add(dataFileReader.next());
		}
		dataFileReader.close();

		return records;
	}
}
