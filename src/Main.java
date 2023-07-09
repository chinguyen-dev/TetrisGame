import controller.GameController;
import model.Board;
import model.patterns.builder.board.BoardBuilder;

public class Main {
    public static void main(String[] args) {
        Board model = new BoardBuilder()
                .width(10)
                .height(20)
                .delayTime(600)
                .build();
        new GameController(model);
    }
}
