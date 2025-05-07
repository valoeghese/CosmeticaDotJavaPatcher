package nz.valoeghese.cdjpatcher;

import cc.cosmetica.api.CosmeticaAPI;
import cc.cosmetica.api.LoreType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CosmeticadotjavaPatcher implements ModInitializer {
	@Override
	public void onInitialize() {
		// e.g. print lores (for testing)
		// also print the message to check it changed
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			CompletableFuture.supplyAsync(() -> CosmeticaAPI.newUnauthenticatedInstance().getLoreList(LoreType.TITLES).or(List.of()))
					.thenAcceptAsync(System.out::println, Minecraft.getInstance())
					.thenApply(v -> CosmeticaAPI.getMessage())
					.thenAccept(System.out::println);
		}

	}
}