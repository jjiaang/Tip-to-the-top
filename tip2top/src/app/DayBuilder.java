package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;


import javafx.animation.Animation.Status;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Builds the scenes / related objects necessary for each day of the game. 
 */
public class DayBuilder {
	
	private static StackPane today = new StackPane();
	// one layer for background (index 0)
	// one layer for character (index 1)
	// one layer for desk/foreground (index 2)
	// one layer for borderpane w buttons n shit to make it interactive?
	
	private static int day = 1;
	//private static int satisfaction;
	//private static int gulagPoints;
	private static ObservableList<String> guests = FXCollections.observableArrayList();
	private static ObservableList<String> email = FXCollections.observableArrayList();
	private static BorderPane amigo = new BorderPane();
	private static Button nextC = new Button();
	private static int index = 0;
	private static boolean dialogueActive = false;
	private static ImageView activeCharacter;
	private static ArrayList<String> active = new ArrayList<>();
	private static Text slot1 = new Text("");
	private static Text slot2 = new Text("");
	private static Text slot3 = new Text("");
	
	//private static ImageView currentCharacter = null;
	
	
	//Pat created for building main screen
		public static StackPane buildMainScreen (Stage window) {
			//StackPane mainPane = new StackPane();
			
			loadDay(today); // loads the images for the opening sequence and display them on top of each other
			
			return today;
		}
	
	
	//Pat created for startday scene 
		
		// save this for the transition between days! (not currently implemented, since I just added onto openingScene)
		public static StackPane startDay(Stage window) {
			StackPane startDay = new StackPane();
			Image image;
			try {
				image = new Image(new FileInputStream("./resources/dayimg/sunup.jpg"));
			    ImageView imageView = new ImageView(image); 
			    startDay.getChildren().add(imageView);
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			
			startDay.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					Scene main = new Scene(DayBuilder.buildMainScreen(window), 1280, 720);
					window.setScene(main);
				}
			});
			return startDay;
		}
		
		//Pat created for end day scene 
		public static StackPane endDay(Stage window) {
			StackPane endDay = new StackPane();
			Image image;
			try {
				image = new Image(new FileInputStream("./resources/dayimg/thesunset.jpg"));
			    ImageView imageView = new ImageView(image); 
			    endDay.getChildren().add(imageView);
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			
			endDay.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					Scene dayIntro = new Scene(DayBuilder.startDay(window), 1280, 720);
	    			window.setScene(dayIntro);
				}
			});
			return endDay;
		}
		//Pat created this to load images for day
		
		
		public static void loadDay (StackPane pane) {
			
			 DropShadow dropShadow = new DropShadow();
			 dropShadow.setRadius(5.0);
			 dropShadow.setOffsetX(8.0);
			 dropShadow.setOffsetY(8.0);
			 dropShadow.setColor(Color.BLACK);
			 
			 DropShadow borderGlow = new DropShadow();
			 borderGlow.setColor(Color.YELLOW);
			 borderGlow.setOffsetX(0f);
			 borderGlow.setOffsetY(0f);
			 borderGlow.setHeight(100.0);
			 borderGlow.setWidth(100.0);
			 
			Image image, image2, image3, image4, image5;
			try {
				image = new Image(new FileInputStream("./resources/dayimg/background.jpg"));
			    ImageView imageView = new ImageView(image); 
			    pane.getChildren().add(imageView);
			    
			    
			    image2 = new Image(new FileInputStream("./resources/dayimg/MrAnything.png"));
			    ImageView imageView2 = new ImageView(image2);
			    imageView2.setManaged(false);
			    imageView2.setLayoutX(1280);
			    imageView2.setLayoutY(150);
			    pane.getChildren().add(imageView2);
			    
			    
			    
			    image3 = new Image(new FileInputStream("./resources/dayimg/desk_b.png"));
			    ImageView imageView3 = new ImageView(image3); 
			    pane.getChildren().add(imageView3);
			    
			    for(int i = 1; i<=12; i++) {
			        image4 = new Image(new FileInputStream("./resources/dayimg/keys/key"+i+".png"));
				    ImageView imageView4 = new ImageView(image4); 
				    imageView4.setEffect(dropShadow);
				    pane.getChildren().add(imageView4);
			    }
			    
			
			    
			    image5 = new Image(new FileInputStream("./resources/dayimg/dialogbox.png"));
			    ImageView imageView5 = new ImageView(image5); 
			    imageView5.setManaged(false);
			    imageView5.setLayoutY(-720);
			    pane.getChildren().add(imageView5);
			    
			    
			    BorderPane handler = new BorderPane();
			    Button accessAmigo = new Button();
			    
			    try {
				    Image amigobtn = new Image(new FileInputStream("./resources/gameimg/amigobtn.png"));
				    Image amigohovered = new Image(new FileInputStream("./resources/gameimg/amigobtnhov.png"));
				    ImageView amigobtng = new ImageView(amigobtn);
				    ImageView amigohoveredg = new ImageView(amigohovered);
				    accessAmigo.setGraphic(amigobtng);
				    accessAmigo.setId("amigo");
				    accessAmigo.graphicProperty().bind(
				    		Bindings.when(accessAmigo.hoverProperty())
		                    .then(amigohoveredg)
		                    .otherwise(amigobtng));

			    } catch (Exception e) { e.printStackTrace();}

			    Image nextCustomer = new Image(new FileInputStream("./resources/gameimg/next.png"));
			    ImageView next = new ImageView(nextCustomer);
			    next.setStyle(null);
			    
			    nextC.setText("Next customer...");
			    nextC.setManaged(false);
			    nextC.setLayoutX(1300);
			    nextC.setLayoutY(650);
			    nextC.setGraphic(next);
			    
			    // change styling n such later
			    
			    handler.setBottom(accessAmigo);
			    handler.setRight(nextC);
			    
			    
			    handler.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
					
					// it appears that the error occurs in the inheritance here somehow?
					
					int top = 0;
					int middle = 1;
					int bottom = 2;
					int clickCount = 1;
					
					@Override
					public void handle(MouseEvent mouseEvent) {
						// advance
						if (dialogueActive) {
							if (clickCount <= active.size() - 2) {
								clickCount++;

								if (top < active.size()) slot1.setText(active.get(top));
								if (middle <= active.size() - 1) slot2.setText(active.get(middle));
								if (bottom <= active.size() - 2) slot3.setText(active.get(bottom));
								top++; middle++; bottom++;
							}
							
							if (active.get(top).equals(" ") && clickCount > 3) {
								active.clear();
								animateButtonIn(nextC);
								slot1.setText(" ");
								slot2.setText(" ");
								slot3.setText(" ");
								dialogueActive = false;
								clickCount = 1;
								top = 0;
								middle = 1;
								bottom = 2;
								animateCharacterOut(activeCharacter);
							}

						} 
					}
				});
			    
			    
			    pane.getChildren().add(handler);
			    runDay(handler);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
			
		}
	
	
		
		public static void runDay (BorderPane handler) {
			if (day == 1) {
				NPC.initializeCharacters(day);
				ArrayList<NPC> dailyCharacters = NPC.getDailyCharacters();
				
				Collections.shuffle(dailyCharacters);
				dailyCharacters.add(new NPC("Tiff"));
				dailyCharacters.add(0, new NPC("Aleksandra"));
				animateButtonIn(nextC);
				
				nextC.setOnAction(e -> {
					
					
					// animate nextC out if index > 0
					
					if (index == 0) {
						animateButtonOut(nextC);
						animateDialogueBoxIn(today.getChildren().get(4)); // 4 is the index of dialogue box in stackpane today
					}
					
					if (index > 0) {
						animateButtonOut(nextC);
						
					}
					
					// stuff for each character
					NPC character = dailyCharacters.get(index);
					
					try {
						Image image = new Image(new FileInputStream("./resources/characters/" + character.getName() + ".png"));
					    ImageView characterView = new ImageView(image); 
					    
					    activeCharacter = characterView;
					    
					    today.getChildren().set(1, characterView); 
					    characterView.setManaged(false);
					    characterView.setLayoutX(1280);
					    characterView.setLayoutY(-20);

					    active.clear();
						for (int i = 0; i < 3; i++) active.add(" ");
					    
					    animateCharacterIn(characterView);
					    
					    
					    
					    //SequentialTransition seqT = new SequentialTransition(animateCharacterIn(characterView).setOnFinished(e -> System.out.println("Holy fuck")));
					    //seqT.play();
					   
					    // some kind of event listener to control the flow of options
						
					    //dialogueActive = true;
					    ArrayList<String> dialogue = NPC.getDialogue(character.getName(), day);
						playDialog(handler, dialogue);
						
						
						//System.out.println("tracker lol");
					
						//NPC.getDialogue(character.getName(),day);	
						// do character dialogue -- listeners / etc will be muted so the player can't screw around while dealing w customers
					    
						
						index++;
						
					    //animateCharacterOut(characterView);
					} catch (Exception c) {c.printStackTrace();}
					
					
					if (index == dailyCharacters.size()) {
						// end the day
					}
					
				});
				
				
				
			}
		}
	
	
	public static void playDialog(BorderPane pane, ArrayList<String> dialog) {
		System.out.println(dialog.toString());
		
		active.addAll(dialog); // we need to separate this so we can ensure it is done correctly?
		for (int i = 0; i < 3; i++) active.add(" ");
		
		
		VBox container = new VBox(5);
		
		
		slot1.setId("dialog-text");
		slot2.setId("dialog-text");
		slot3.setId("dialog-text");
		
		slot1.setFill(Color.GHOSTWHITE);
		slot2.setFill(Color.GHOSTWHITE);
		slot3.setFill(Color.GHOSTWHITE);
		
	    container.setManaged(false);
	    container.setLayoutX(45);
	    container.setLayoutY(70);
		container.getChildren().addAll(slot1, slot2, slot3);
		
		pane.getChildren().add(container);
		
		int length = active.size();
		
		
		
	}
	
	
	public static void animateTextIn() {
		
	}
	
	public static void animateTextOut() {
		
	}
	
	public static void buildAmigoScreen () {
		
		// set the background image
		try {
			Image background = new Image(new FileInputStream("./resources/menuimg/amigo.jpg"));
			BackgroundImage bgImg = new BackgroundImage(background,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    null);
			Background emailbkg = new Background(bgImg);
			amigo.setBackground(emailbkg);
		} catch (Exception e) {}
		
		Button viewGuests = new Button();
		Button viewEmails = new Button();
		Button exit = new Button();
		
		VBox amigoButtons = new VBox(20);
		amigoButtons.getChildren().addAll(viewGuests, viewEmails, exit);
		
		
		amigo.getChildren().add(amigoButtons);
		
		//viewGuests.setOnAction(e -> amigo.setCenter(checkins));
		//viewEmails.setOnAction(e -> amigo.setCenter(emails));
	
	}
	
	public static void buildEmailScreen() { // focus on this AFTER the email screen is built
		try {
			amigo.getChildren().clear();
			Image background = new Image(new FileInputStream("./resources/menuimg/email.jpg"));
			BackgroundImage bgImg = new BackgroundImage(background,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    null);
			Background emailbkg = new Background(bgImg);
			amigo.setBackground(emailbkg);
		} catch (Exception e) {}
		
		ListView<String> emails = new ListView<>(email);
		if (email.isEmpty()) email.add("You have no new emails!");
		
		amigo.getChildren().add(emails);
		
	}
	
	public static void buildCheckInScreen(ArrayList<NPC> dailyCharacters) {
		try {
			// clearing the borderpane and setting an appropriate background
			amigo.getChildren().clear();
			Image background = new Image(new FileInputStream("./resources/menuimg/checkin.jpg"));
			BackgroundImage bgImg = new BackgroundImage(background,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    null);
			Background checkinbkg = new Background(bgImg);
			amigo.setBackground(checkinbkg);
		} catch (Exception e) {}
		
		// building actual elements
		// initialize background 
		ListView<String> guestlist = new ListView<>(guests);
		TextField guestName = new TextField("Name");
		TextField roomNumber = new TextField("Room#");
		Button addGuest = new Button();
		Button checkOut = new Button();
		Button back = new Button();
		
		HBox addName = new HBox(20);
		addName.getChildren().addAll(guestName, roomNumber, addGuest);
		
		VBox buttons = new VBox(20);
		buttons.getChildren().addAll(checkOut, back);
		
		
		
		amigo.setTop(addName);
		amigo.setCenter(guestlist);
		amigo.setRight(buttons);
		
		
		// event handling for buttons 
		
		addGuest.setOnAction(e -> {
			NPC temp = new NPC(guestName.getText(), Integer.parseInt(roomNumber.getText().replaceAll("[\\D]", "")));
			guests.add(temp.toString());
		});
		checkOut.setOnAction(e -> guests.remove(guestlist.getSelectionModel().getSelectedIndex()));
		back.setOnAction(e -> {
			amigo.getChildren().clear();
			buildAmigoScreen();
		});
	}
	
	/**
	 * This function will review activity in the Amigo 1000 for the day, and add points to satisfaction/gulag points as needed. 
	 */
	public static void verifyAmigo() {
		
	}
	
	// Animations
	
	public static TranslateTransition animateCharacterIn (Node character) {
		TranslateTransition translate = new TranslateTransition();
		translate.setDuration(Duration.millis(1300)); 
		translate.setNode(character);
		translate.setByX(-590);
		translate.setCycleCount(1); 
		translate.setAutoReverse(false); 
		translate.setOnFinished(e -> dialogueActive = true);
		
		translate.play();
		return translate;
	}
	
	
	// reverses animate character in?
	public static TranslateTransition animateCharacterOut (Node character) {
		TranslateTransition translate = new TranslateTransition();
		translate.setDuration(Duration.millis(1300)); 
		translate.setNode(character);
		translate.setByX(590);
		translate.setCycleCount(1); 
		translate.setAutoReverse(false); 
		translate.play(); 
		//translate.setOnFinished(e -> );
		return translate;
		
		
	}
	
	public static TranslateTransition animateButtonIn (Node button) {
		TranslateTransition translate = new TranslateTransition();
		translate.setDuration(Duration.millis(1300)); 
		translate.setNode(button);
		translate.setByX(-350);
		translate.setCycleCount(1); 
		translate.setAutoReverse(false); 
		translate.play(); 
		return translate;
		
	}
	
	public static TranslateTransition animateButtonOut (Node button) {
		TranslateTransition translate = new TranslateTransition();
		translate.setDuration(Duration.millis(1300)); 
		translate.setNode(button);
		translate.setByX(370);
		translate.setCycleCount(1); 
		translate.setAutoReverse(false); 
		translate.play(); 
		return translate;
		
	}
	
	public static TranslateTransition animateDialogueBoxIn (Node image) {
		
		TranslateTransition translate = new TranslateTransition();
		translate.setDuration(Duration.millis(1300)); 
		translate.setNode(image);
		translate.setByY(720);
		translate.setCycleCount(1); 
		translate.setAutoReverse(false); 
		translate.play(); 
		//translate.setOnFinished(e -> dialogueActive = true);
		return translate;
		
	}
	
	
	
	// I heard you like light theme
	
}
