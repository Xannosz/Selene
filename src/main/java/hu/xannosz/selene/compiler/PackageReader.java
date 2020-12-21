package hu.xannosz.selene.compiler;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PackageReader {

	private String pack;
	private List<String> files = new ArrayList<>();

	public PackageReader(String pack) {
		this.pack = pack;
	}

	public void read() {
		Path packPath = Paths.get(pack);
	}

}
