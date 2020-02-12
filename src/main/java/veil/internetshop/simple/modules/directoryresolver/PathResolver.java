package veil.internetshop.simple.modules.directoryresolver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class specifies on folders structure control
 */
public class PathResolver{

	private ComplexIdentifier complexIdentifier;

	private int filesCount;

	private final Path root;

	/**
	 * Constructor with two parameters
	 *
	 * @param root       root directory
	 * @param pattern    key, that provides rule for directories structure. @see ComplexIdentifier#ComplexIdentifier(String)
	 * @param filesCount count of files in directory when folder must be changed
	 */
	public PathResolver(final String root, final String pattern, final int filesCount) throws IOException{
		this.root = Paths.get(root);
		if(! Files.exists(this.root)){
			throw new IOException("Invalid root folder");
		}
		this.filesCount = filesCount;
		complexIdentifier = new ComplexIdentifier(pattern, findLastId(new ArrayList<>(), this.root));
	}

	private String findLastId(List<String> key, Path folder){
		File[] folders = folder.toFile().listFiles(File::isDirectory);
		if(folders != null && folders.length > 0){
			File lastFolder = folders[folders.length - 1];
			key.add(lastFolder.getName());
			return findLastId(key, lastFolder.toPath());
		}
		return key.stream()
				.map(StringNumberUtil::getIndex)
				.map(String::valueOf)
				.collect(Collectors.joining(":"));

	}

	/**
	 * Returns folder which contains at least one slot for file, by established rules
	 *
	 * @return folder which contains at least one slot for file, by established rules
	 * @throws IOException io errors occurs
	 */
	public Path findPath() throws IOException{
		Path target = root.resolve(complexIdentifier.getPath());
		while(! isFolderValid(target)){
			target = createNewFolder(root);
		}
		return target;
	}

	private Path createNewFolder(Path parent) throws IOException{
		if(! complexIdentifier.increment()){
			throw new IOException("Folder path overflow");
		}
		Path folder = parent.resolve(complexIdentifier.getPath());
		Files.createDirectories(folder);
		return folder;
	}

	private boolean isFolderValid(Path folder) throws IOException{
		if(! Files.exists(folder)){
			Files.createDirectories(folder);
			return true;
		}
		return folder.toFile().listFiles().length < filesCount;
	}

	public Path getRoot(){
		return root;
	}
}
