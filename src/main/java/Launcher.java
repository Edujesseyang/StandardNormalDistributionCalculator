import View.MainPage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainPage mainPage = new MainPage(null, null);
        mainPage.show();
    }
}
