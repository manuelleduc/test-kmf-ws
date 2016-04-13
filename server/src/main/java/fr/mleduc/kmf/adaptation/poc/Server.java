package fr.mleduc.kmf.adaptation.poc;

import org.kevoree.modeling.plugin.RocksDBPlugin;
import org.kevoree.modeling.plugin.WebSocketGateway;
import org.rocksdb.RocksDBException;

import java.io.IOException;

/**
 * Created by mleduc on 12/04/16.
 */
public class Server {

    public static final int PORT = 8085;

    public static void main(String[] args) throws IOException, RocksDBException {
        final RocksDBPlugin rocksDBPlugin = new RocksDBPlugin("/tmp/adaptation-poc");
        rocksDBPlugin.connect(throwable -> {
            final WebSocketGateway wsSocketGateway = WebSocketGateway.expose(rocksDBPlugin, PORT);
            wsSocketGateway.start();
        });

    }
}
