package top.zgx8.netty.im.client;

public class Startup {

    public static void main(String[] args) throws Exception {
        new Client().run("127.0.0.1", 1024);
    }
}
