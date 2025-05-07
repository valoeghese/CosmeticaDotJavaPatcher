package nz.valoeghese.cdjpatcher.mixin;

import cc.cosmetica.impl.CosmeticaWebAPI;
import cc.cosmetica.util.HostProvider;
import cc.cosmetica.util.Response;
import cc.cosmetica.util.Yootil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(value = CosmeticaWebAPI.class, remap = false)
public class CDJSkipGetAPIMixin {
	@Shadow private static boolean enforceHttpsGlobal;
	@Shadow private static HostProvider apiHostProviderTemplate;
	@Shadow private static String authApiServerHost;
	@Shadow private static String websiteHost;
	@Shadow private static String authServerHost;
	@Shadow private static String message;

	/**
	 * @author Valoeghese
	 * @reason CosmeticaDotJava gets the cosmetica api url from https://cosmetica.cc/getapi which sometimes
	 * returns an HTML error code, crashing the game. The API url(s) for Cosmetica 1.x are known and will not change.
	 */
	@Overwrite
	private static void retrieveAPIIfNoneCached() throws IllegalStateException {
		if (apiHostProviderTemplate == null) {
			// Values from https://cosmetica.cc/getapi
			apiHostProviderTemplate = new HostProvider("https://api.cosmetica.cc", enforceHttpsGlobal);
			authApiServerHost = "https://auth.cosmetica.cc";
			websiteHost = "https://cosmetica.cc";
			authServerHost = "auth.cosmetics.eyezah.com:25596";
			// Change message
			message = "/getapi skipped. Going directly to Cosmetica API.";
		}
	}
}