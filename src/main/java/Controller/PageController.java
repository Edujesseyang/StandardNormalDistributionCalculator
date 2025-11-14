package Controller;

import Model.DataTable;
import View.MainPage;
import javafx.stage.Stage;

public class PageController {
    private final DataTable dataTable = DataTable.getInstance();
    private final Stage page;

    public PageController(Stage page) {
        this.page = page;
    }

    public void getProbability(String input) {
        page.close();
        input = input.trim();
        input = checkValidity(input);
        double parsedInput = Double.parseDouble(input);
        double ans;
        if (parsedInput < 0.0) {
            ans = 1.0 - dataTable.findP((int) (Math.abs(parsedInput * 100)));
        } else {
            ans = dataTable.findP((int) (parsedInput * 100));
        }
        MainPage newPage = new MainPage(parsedInput, ans);
        newPage.show();
    }

    public void getXValue(String input) {
        page.close();
        input = input.trim();
        input = checkValidity(input);
        double parsedInput = Double.parseDouble(input);
        parsedInput = Math.round(parsedInput * 10000.0) / 10000.0;

        int ansIndex;
        if (parsedInput < 0.5) {
            ansIndex = dataTable.findX(1 - parsedInput);
            ansIndex = ansIndex == -1 ? -358 : -ansIndex;
        } else {
            ansIndex = dataTable.findX(parsedInput);
            ansIndex = ansIndex == -1 ? 358 : ansIndex;
        }
        double ans = ansIndex / 100.0;
        MainPage newPage = new MainPage(ans, parsedInput);
        newPage.show();
    }

    public void clear() {
        page.close();
        MainPage newPage = new MainPage(null, null);
        newPage.show();
    }

    private String checkValidity(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                if (c == '-' || c == '+' || c == '.') continue;
                return "0.0";
            }
        }
        return input;
    }
}
