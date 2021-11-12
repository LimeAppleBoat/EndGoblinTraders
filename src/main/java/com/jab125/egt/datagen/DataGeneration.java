package com.jab125.egt.datagen;

import com.jab125.util.datagen.DataGeneraton;
import net.hat.gt.datagen.GoblinTradeProvider;
import net.minecraft.data.DataGenerator;

public class DataGeneration extends DataGeneraton {
    public DataGeneration() {

    }

    public static void registerCommonProviders(DataGenerator generator) {
        System.out.println("REGISTERED");
        generator.install(new EndGoblinTradeProvider(generator));

        try {
            generator.run();
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }
}
