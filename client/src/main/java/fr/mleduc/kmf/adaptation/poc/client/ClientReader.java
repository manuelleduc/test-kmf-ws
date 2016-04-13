package fr.mleduc.kmf.adaptation.poc.client;

import fr.mleduc.adaptation.PocModel;
import gol.meta.MetaCell;
import org.kevoree.modeling.KObject;
import org.kevoree.modeling.cdn.KContentDeliveryDriver;
import org.kevoree.modeling.memory.manager.DataManagerBuilder;
import org.kevoree.modeling.plugin.WebSocketClientPlugin;

/**
 * Created by mleduc on 13/04/16.
 */
public class ClientReader {
    public static void main(String[] args) {
        final String url = "ws://localhost:" + 8085 + "/adaptationpoc";
        System.out.println(url);
        final KContentDeliveryDriver ws = new WebSocketClientPlugin(url);
        final PocModel model = new PocModel(DataManagerBuilder.create().withContentDeliveryDriver(ws).build());
        model.connect(o -> model.findAll(MetaCell.getInstance(), 0, System.currentTimeMillis(), kObjects -> {
            for (KObject kObject : kObjects) {
                System.out.println(kObject);
            }
            /*model.disconnect(o1 -> {

            });*/
        }));
    }
}
