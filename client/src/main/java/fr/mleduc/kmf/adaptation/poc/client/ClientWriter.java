package fr.mleduc.kmf.adaptation.poc.client;

import fr.mleduc.adaptation.PocModel;
import fr.mleduc.kmf.adaptation.poc.business.ClientBusiness;
import org.kevoree.modeling.cdn.KContentDeliveryDriver;
import org.kevoree.modeling.memory.manager.DataManagerBuilder;
import org.kevoree.modeling.plugin.WebSocketClientPlugin;

/**
 * Created by mleduc on 12/04/16.
 */
public class ClientWriter {
    public static void main(String[] args) {
        final String url = "ws://localhost:" + 8085 + "/adaptationpoc";
        System.out.println(url);
        final KContentDeliveryDriver ws = new WebSocketClientPlugin(url);
        final PocModel model = new PocModel(DataManagerBuilder.create().withContentDeliveryDriver(ws).build());
        model.connect(o -> {
            final ClientBusiness clientBusiness = new ClientBusiness(model);
            try {
                do {
                } while (!clientBusiness.read());
            } finally {
                model.disconnect(o1 -> {
                });
            }
        });
    }
}
