package application.Controller;

import application.Exception.BlankInputException;
import application.Exception.RetypeException;
import application.Exception.UsernameMismatchException;
import application.Exception.WrongPasswordException;
import application.Model.ErrorAlert;
import application.Model.SuccessAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AccountDashboardController extends DashBoardController {

	@FXML
	private Button back;

	@FXML
	private TextField currentPasswordField;

	@FXML
	private TextField currentUsernameField;

	@FXML
	private TextField firstNameField;

	@FXML
	private MenuItem firstNameMenu;

	@FXML
	private Pane firstNameView;

	@FXML
	private Button firstnameBtn;

	@FXML
	private TextField lastNameField;

	@FXML
	private MenuItem lastNameMenu;

	@FXML
	private MenuItem backMenu;

	@FXML
	private Pane lastNameView;

	@FXML
	private Button lastnameBtn;

	@FXML
	private Pane logoView;

	@FXML
	private TextField newPasswordField;

	@FXML
	private TextField newUsernameField;

	@FXML
	private Button passwordBtn;

	@FXML
	private MenuItem passwordMenu;

	@FXML
	private Pane passwordView;

	@FXML
	private TextField reTypeLastNameField;

	@FXML
	private TextField reTypeNewPasswordField;

	@FXML
	private TextField reTypeUsername;

	@FXML
	private TextField reTypefirstNameField;

	@FXML
	private Label subscriptionLabel;

	@FXML
	private MenuItem upgradeToVipMenu;

	@FXML
	private Button usernameBtn;

	@FXML
	private MenuItem usernameMenu;

	@FXML
	private Button usernameSaveBtn;

	@FXML
	private Pane usernameView;

	@FXML
	private Button vipBtn;

	ErrorAlert alert = ErrorAlert.getInstance();

	SuccessAlert alertSuccess = SuccessAlert.getInstance();

	@FXML
	public void backToHomePage(ActionEvent event) {
		back.getScene().getWindow().hide();
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/dashboard.fxml"));
			Parent parentNode = loader.load();
			Scene scene = new Scene(parentNode);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("Data Anylytics Hub");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void changePage(ActionEvent event) {
		logoView.setVisible(false);
		if ((event.getSource() == usernameBtn) || (event.getSource() == usernameMenu)) {
			usernameView.setVisible(true);
			passwordView.setVisible(false);
			firstNameView.setVisible(false);
			lastNameView.setVisible(false);
		} else if ((event.getSource() == passwordBtn) || (event.getSource() == passwordMenu)) {
			usernameView.setVisible(false);
			passwordView.setVisible(true);
			firstNameView.setVisible(false);
			lastNameView.setVisible(false);
		} else if ((event.getSource() == firstnameBtn) || (event.getSource() == firstNameMenu)) {
			usernameView.setVisible(false);
			passwordView.setVisible(false);
			firstNameView.setVisible(true);
			lastNameView.setVisible(false);
		} else if ((event.getSource() == lastnameBtn) || (event.getSource() == lastNameMenu)) {
			usernameView.setVisible(false);
			passwordView.setVisible(false);
			firstNameView.setVisible(false);
			lastNameView.setVisible(true);
		}
	}

	@FXML
	public void changeUserName(ActionEvent event) {
		ChangeUsername changeUsernameController = ChangeUsername.getInstance();
		String currentUn = currentUsernameField.getText();
		String newUn = newUsernameField.getText();
		String reTypeUn = reTypeUsername.getText();
		try {
			changeUsernameController.checkBlankField(currentUn, newUn, reTypeUn);
			changeUsernameController.checkMatchingCurrentUsername(currentUn, currentUserAccount.getUsername());
			changeUsernameController.checkMatchingRetypeUsername(newUn, reTypeUn);
			changeUsernameController.checkUsernameExist(currentUserAccount.getUsername(), newUn);
			if (!changeUsernameController.checkInputWhiteSpace(currentUn, newUn, reTypeUn)) {
				currentUserAccount.setUsername(newUn);
				// update database
				UserDatabase udb = UserDatabase.getInstance();
				udb.updateUsername(currentUn, newUn);
				alertSuccess.alertUpdateUsernameSuccess();
			}

		} catch (UsernameMismatchException e) {
			alert.alertInvalidUsername();
		} catch (RetypeException e) {
			alert.alertRetypeUsername();
		} catch (BlankInputException e) {
			alert.alertBlankInput();
		}
	}

	@FXML
	public void changePassword(ActionEvent event) {
		ChangePassword changePwController = ChangePassword.getInstance();
		String currentPw = currentPasswordField.getText();
		String newPw = newPasswordField.getText();
		String reTypePw = reTypeNewPasswordField.getText();
		try {
			changePwController.checkBlankField(currentPw, newPw, reTypePw);
			changePwController.checkCurrentPassword(currentPw);
			changePwController.checkMatchingRetypePassword(newPw, reTypePw);
			if (!changePwController.checkInputWhiteSpace(currentPw, newPw, reTypePw)) {
				currentUserAccount.setPassword(newPw);
				// update database
				UserDatabase udb = UserDatabase.getInstance();
				udb.updatePassword(currentUserAccount.getUsername(), newPw);
				alertSuccess.alertUpdatePasswordSuccess();
			}
		} catch (BlankInputException e) {
			alert.alertBlankInput();
		} catch (WrongPasswordException e) {
			alert.alertInvalidPassword();
		} catch (RetypeException e) {
			alert.alertRetypePassword();
		}
	}

	@FXML
	public void changeFirstName(ActionEvent event) {
		ChangeFirstName changeFnController = ChangeFirstName.getInstance();
		String newFirstName = firstNameField.getText();
		String reTypeFirstName = reTypefirstNameField.getText();
		try {
			changeFnController.checkBlankField(newFirstName, reTypeFirstName);
			changeFnController.checkMatchingRetypeFirstName(newFirstName, reTypeFirstName);
			if (!changeFnController.checkInputWhiteSpace(newFirstName, reTypeFirstName)) {
				currentUserAccount.setFirstname(newFirstName);
				// update database
				UserDatabase udb = UserDatabase.getInstance();
				udb.updateFirstName(currentUserAccount.getUsername(), reTypeFirstName);
				alertSuccess.alertUpdateFirstNameSuccess();
			}
		} catch (BlankInputException e) {
			alert.alertBlankInput();
		} catch (RetypeException e) {
			alert.alertRetypeFirstname();
		}
	}

	@FXML
	public void changeLastName(ActionEvent event) {
		ChangeLastName changeLnController = ChangeLastName.getInstance();
		String newLastName = lastNameField.getText();
		String reTypeLastName = reTypeLastNameField.getText();
		try {
			changeLnController.checkBlankField(newLastName, reTypeLastName);
			changeLnController.checkMatchingRetypeLastName(newLastName, reTypeLastName);
			if (!changeLnController.checkInputWhiteSpace(newLastName, reTypeLastName)) {
				currentUserAccount.setLastname(newLastName);
				// update database
				UserDatabase udb = UserDatabase.getInstance();
				udb.updateLastName(currentUserAccount.getUsername(), reTypeLastName);
				alertSuccess.alertUpdateLastNameSuccess();
			}
		} catch (BlankInputException e) {
			alert.alertBlankInput();
		} catch (RetypeException e) {
			alert.alertRetypeLastname();
		}
	}

	// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Alert.html
	@FXML
	public void upgradeToVip(ActionEvent event) {
		Alert alertConfirmation = new Alert(AlertType.CONFIRMATION,
				"Would you like to subscribe to the application for a monthly fee of $0?");
		alertConfirmation.showAndWait().filter(response -> response == ButtonType.OK)
				.ifPresent(response -> setVipUser());
	}

	public void initialize() {
		hideVipButton();
	}

	public void setVipUser() {
		alertSuccess.alertUpdatePlanSuccess();
		currentUserAccount.setUserPlan("VIP");
		UserDatabase udb = UserDatabase.getInstance();
		udb.updatePlan(currentUserAccount.getUsername());
	}

	public void hideVipButton() {
		if (currentUserAccount.getUserPlan().equals("VIP")) {
			subscriptionLabel.setText("   You account is VIP");
			subscriptionLabel.setFont(new Font("Arial", 11));
			upgradeToVipMenu.setVisible(false);
			vipBtn.setVisible(false);
		}
	}
}