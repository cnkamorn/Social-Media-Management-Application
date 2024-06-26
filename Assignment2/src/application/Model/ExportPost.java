package application.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import application.Exception.BlankInputException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class is a an export class It contains related methods about export the
 * post
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class ExportPost {

	private static ExportPost Instance;

	private ExportPost() {
	};

	public static ExportPost getInstance() {
		if (Instance == null) {
			Instance = new ExportPost();
		}
		return Instance;
	}

	/**
	 * Method to check the blank field
	 * 
	 * @param postId
	 * @throws BlankInputException
	 */
	public void checkBlankField(String postId) throws BlankInputException {
		if (postId.isBlank()) {
			throw new BlankInputException("Error:Input from the post Id field is blank");
		}
	}

	/**
	 * Method to export the file
	 * 
	 * @param post
	 * @throws FileNotFoundException
	 */
	public void exportFile(Post post) throws FileNotFoundException {
		Integer postID = post.getPostID();
		String postContent = post.getPostContent();
		Integer postLikes = post.getLikes();
		Integer postShares = post.getShares();
		String postDateTime = post.getPostDateTime();
		String postAuthorID = post.getPostAuthorID();
		FileChooser chooser = new FileChooser();
		chooser.setInitialFileName("post.csv"); // set the file name
		File file = chooser.showSaveDialog(new Stage()); // show a save file window
		if (file != null) {
			PrintWriter writer = new PrintWriter(file);
			writer.write("ID,content,author,likes,shares,date-time\n");
			writer.write(String.format("%d,%s,%s,%d,%d,%s", postID, postContent, postAuthorID, postLikes, postShares,
					postDateTime));
			writer.close();
		}
	}
}
