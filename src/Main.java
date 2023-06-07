import model.patterns.builder.game.GameBuilder;
import view.Game;

public class Main {
    public static void main(String[] args) {
        Game g = new GameBuilder()
                .title("Tetris Game")
                .width(445)
                .height(629)
                .build();
    }
}
