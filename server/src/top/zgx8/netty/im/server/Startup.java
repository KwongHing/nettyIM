package top.zgx8.netty.im.server;

public class Startup {

    public static void main(String[] args) throws Exception {
	// write your code here
        new Server().run(1024);
    }
}
