package behavioral.state;

/**
 * Created by Arsen on 5/15/2017
 */
public class StateApp {
    public static void main(String[] args) {
        StateContext ctx = new StateContext(new LowerCase());
        ctx.write("Hello!");
        ctx.write("Hello!");
        ctx.write("Hello!");
        ctx.write("Hello!");
        ctx.write("Hello!");
        ctx.write("Hello!");
    }
}

interface State {
    void write(final StateContext ctx, final String content);
}

class UpperCase implements State {
    public void write(StateContext ctx, String content) {
        System.out.println(content.toUpperCase());
        ctx.setState(new LowerCase());
    }
}

class LowerCase implements State {
    public void write(StateContext ctx, String content) {
        System.out.println(content.toLowerCase());
        ctx.setState(new UpperCase());
    }
}

class StateContext {
    private State state;
    StateContext(State state) {
        this.state = state;
    }
    void setState(State state) {
        this.state = state;
    }
    void write(String name) {
        state.write(this, name);
    }
}