package nz.valoeghese.cdjpatcher;

import cc.cosmetica.api.CosmeticaAPI;
import cc.cosmetica.api.LoreType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class CosmeticadotjavaPatcher implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// e.g. print lores (for testing)
		// also print the message to check it changed
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			CompletableFuture.supplyAsync(() -> CosmeticaAPI.newUnauthenticatedInstance().getLoreList(LoreType.TITLES).or(Collections.emptyList()))
					.thenAcceptAsync(System.out::println, Minecraft.getInstance())
					.thenApply(v -> CosmeticaAPI.getMessage())
					.thenAccept(System.out::println);
		}

	}
}