
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.uaio.stealdogs.init;

import org.lwjgl.glfw.GLFW;

import net.uaio.stealdogs.network.StealdogsupMessage;
import net.uaio.stealdogs.network.StealdogsdownMessage;
import net.uaio.stealdogs.StealDogsMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class StealDogsModKeyMappings {
	public static final KeyMapping STEALDOGSDOWN = new KeyMapping("key.steal_dogs.stealdogsdown", GLFW.GLFW_KEY_X, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				StealDogsMod.PACKET_HANDLER.sendToServer(new StealdogsdownMessage(0, 0));
				StealdogsdownMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping STEALDOGSUP = new KeyMapping("key.steal_dogs.stealdogsup", GLFW.GLFW_KEY_Z, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				StealDogsMod.PACKET_HANDLER.sendToServer(new StealdogsupMessage(0, 0));
				StealdogsupMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(STEALDOGSDOWN);
		event.register(STEALDOGSUP);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				STEALDOGSDOWN.consumeClick();
				STEALDOGSUP.consumeClick();
			}
		}
	}
}
