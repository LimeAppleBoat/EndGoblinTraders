package com.jab125.egt.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataProvider;

public class DataGeneration implements DataGeneratorEntrypoint {
    /**
     * Register {@link DataProvider} with the {@link FabricDataGenerator} during this entrypoint.
     *
     * @param generator The {@link FabricDataGenerator} instance
     */
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        generator.addProvider(new EndGoblinTradeProvider(generator));
        System.out.println("ADDING");
        try {
            generator.run();
            System.out.println("RAN");
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }
}
